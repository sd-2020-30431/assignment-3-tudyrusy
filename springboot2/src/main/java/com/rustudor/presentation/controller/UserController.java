package com.rustudor.presentation.controller;


import com.rustudor.Dto.*;
import com.rustudor.business.Services.UserService;
import com.rustudor.Util.DataValidator;
import com.rustudor.Util.RequestValidator;
import com.rustudor.Util.Session;
import com.rustudor.Util.SessionManager;
import com.rustudor.business.mediator.Mediator;
import com.rustudor.business.mediator.comand.AddItemCommand;
import com.rustudor.business.mediator.comand.RegisterCommand;
import com.rustudor.business.mediator.comand.SetConsumptionCommand;
import com.rustudor.business.mediator.comand.SetGoalCommand;
import com.rustudor.business.mediator.handler.*;
import com.rustudor.business.mediator.query.*;
import com.rustudor.business.mediator.response.*;
import com.rustudor.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private Mediator mediator;

    @GetMapping(value = "/getRole")
    public ResponseEntity<Role> getRole(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(session.getRole(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody FullUserDto fullUserDto) {
        
        System.out.println(fullUserDto);
        if (!DataValidator.validateUser(fullUserDto)) {
            System.out.println("user input error");
            return new ResponseEntity<>("INVALID DATA", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        RegisterCommand c = new RegisterCommand(fullUserDto);
        RegisterCommandHandler h = (RegisterCommandHandler) mediator.<RegisterCommand,RegisterCommandResponse>getHandler(c);
        RegisterCommandResponse r = h.handle(c);
        switch (r.getResponse()) {
            case 0:
                return new ResponseEntity<>("SUCCESS : USER REGISTERED", HttpStatus.OK);
            case -1:
                return new ResponseEntity<>("DUPLICATE", HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<>("UNKNOWN ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getItems")
    public ResponseEntity<ArrayList<ItemDto1>> getAccounts(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            GetItemsQuery getItemsQuery= new GetItemsQuery(session);
            GetItemsQueryHandler getItemsQueryHandler = (GetItemsQueryHandler) mediator.<GetItemsQuery, GetItemsQueryResponse>getHandler(getItemsQuery);
            GetItemsQueryResponse getItemsQueryResponse = getItemsQueryHandler.handle(getItemsQuery);
            ArrayList<ItemDto1> itemDtos = getItemsQueryResponse.getItems();
            return new ResponseEntity<>(itemDtos, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addItem")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto, @RequestHeader("token") String token) {
        //validation
        if(DataValidator.validateItem(itemDto)) {
            Session session = SessionManager.getSessionMap().get(token);
            AddItemCommand c = new AddItemCommand(itemDto,session);
            AddItemCommandHandler h = (AddItemCommandHandler)mediator.<AddItemCommand,AddItemCommandResponse>getHandler(c);
            AddItemCommandResponse r = h.handle(c);
            
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("item input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/setGoal")
    public ResponseEntity setGoal(@RequestBody String goal, @RequestHeader("token") String token) {
        //validation
        if(DataValidator.validateGoal(goal)) {
            Session session = SessionManager.getSessionMap().get(token);
            SetGoalCommand c = new SetGoalCommand(Integer.parseInt(goal),session);
            SetGoalCommandHandler h = (SetGoalCommandHandler)mediator.<SetGoalCommand,SetGoalCommandResponse>getHandler(c);
            SetGoalCommandResponse r = h.handle(c);
            
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("goal input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/setConsumption")
    public ResponseEntity setConsumption(@RequestBody ConsumptionDto consumptionDto, @RequestHeader("token") String token) {
        if(DataValidator.validateConsumptionDto(consumptionDto)) {
            Session session = SessionManager.getSessionMap().get(token);
            SetConsumptionCommand c = new SetConsumptionCommand(consumptionDto,session);
            SetConsumptionCommandHandler h = (SetConsumptionCommandHandler)mediator.<SetConsumptionCommand,SetConsumptionCommandResponse>getHandler(c);
            SetConsumptionCommandResponse r = h.handle(c);

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println("consumptionDto input error");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String token) {
        if (!SessionManager.getSessionMap().containsKey(token))
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        else {
            LogoutQuery q = new LogoutQuery(token);
            LogoutQueryHandler h = (LogoutQueryHandler) mediator.<LogoutQuery,LogoutQueryResponse>getHandler(q);
            LogoutQueryResponse r = h.handle(q);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        LoginQuery loginQuery= new LoginQuery(loginDto);
        LoginQueryHandler loginQueryHandler = (LoginQueryHandler) mediator.<LoginQuery, LoginQueryResponse>getHandler(loginQuery);
        LoginQueryResponse loginQueryResponse = loginQueryHandler.handle(loginQuery);
        TokenDto tokenDto = loginQueryResponse.getTokenDto();
        //System.out.println("ok");
        System.out.println(loginDto.getUsername());
        if (tokenDto != null)
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(tokenDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getWeeklyReport")
    public ResponseEntity<StringObj> getWeeklyReport(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            GetWReportQuery q = new GetWReportQuery(session);
            GetWReportQueryHandler h = (GetWReportQueryHandler) mediator.<GetWReportQuery, GetWReportQueryResponse>getHandler(q);
            GetWReportQueryResponse r = h.handle(q);
            return new ResponseEntity<>(r.getReport(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getMonthlyReport")
    public ResponseEntity<StringObj> getMonthlyReport(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            GetMReportQuery q = new GetMReportQuery(session);
            GetMReportQueryHandler h = (GetMReportQueryHandler) mediator.<GetMReportQuery, GetMReportQueryResponse>getHandler(q);
            GetMReportQueryResponse r = h.handle(q);
            return new ResponseEntity<>(r.getReport(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/viewProfile")
    public ResponseEntity<UserDto> viewProfile(@RequestHeader("token") String token) {
        Session session = SessionManager.getSessionMap().get(token);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            if (!RequestValidator.validate(session))
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            ViewProfileQuery q= new ViewProfileQuery(session.getUsername());
            ViewProfileQueryHandler h = (ViewProfileQueryHandler) mediator.<ViewProfileQuery, ViewProfileQueryResponse>getHandler(q);
            ViewProfileQueryResponse r = h.handle(q);
            return new ResponseEntity<>(r.getUserDto(), HttpStatus.OK);
        }
    }
}
