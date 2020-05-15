import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserModel} from './user.model';
import {HttpHeaders} from '@angular/common/http';
import {ItemModel} from './item.model';
import {ItemModel1} from './item1.model';
import {CdModel} from './cd.model';
import {StringObj} from './stringObj.model';
import {WasteObserver} from './WasteObserver';
import {ConcreteSubject} from './ConcreteSubject';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

export class WelcomeComponent implements OnInit {
  actualToken: string;
  userModel: UserModel = new UserModel();
  admin = false;
  item;
  color = 0;
  stringObj: StringObj = new StringObj();
  itemModel: ItemModel = new ItemModel();
  itemsModel: ItemModel1[];
  cdModel: CdModel = new CdModel();
  goal: number;
  wasteO = new WasteObserver();
  subject = new ConcreteSubject();

  constructor(private http: HttpClient, private router: Router) {
  }

  add() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.post('http://localhost:8080/users/addItem', this.itemModel, httpOptions).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.getItems();
    }, 2000);
  }

  viewProfile() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get<UserModel>('http://localhost:8080/users/viewProfile', httpOptions).subscribe(
      newUser => {
        this.userModel = newUser;
        console.table(this.userModel);
      },
      error => console.log(error)
    );
    setTimeout(() => {
      this.subject.getWaste(this.itemsModel, this.userModel.goal);
    }, 1500);
  }

  getItems() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };
    this.http.get<ItemModel1[]>('http://localhost:8080/users/getItems',
      httpOptions).subscribe(result => {
        this.itemsModel = result;
        console.table(this.itemsModel);
      },
      error => console.log(error));
    setTimeout(() => {
      this.subject.getWaste(this.itemsModel, this.userModel.goal);
    }, 1500);
  }

  logout() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get('http://localhost:8080/users/logout', httpOptions).subscribe(result => console.log(result), error => console.log(error));
    this.router.navigateByUrl('/login');
  }

  ngOnInit() {
    this.subject.attach(this.wasteO);
    this.actualToken = window.localStorage.getItem('token');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get('http://localhost:8080/users/getRole', httpOptions).subscribe(result => {
      console.log(result);
      if (result.toString() === 'ADMIN') {
        this.admin = true;
      }
    }, error => console.log(error));
    this.viewProfile();
    this.getItems();
  }

  cd() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.item = (document.getElementById('item')) as HTMLSelectElement;
    this.cdModel.name = this.item.options[this.item.selectedIndex].text.toString();
    this.http.post('http://localhost:8080/users/setConsumption', this.cdModel,
      httpOptions).subscribe(
      result => {
        console.log(result);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.getItems();
    }, 2000);
  }

  setGoal() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.post('http://localhost:8080/users/setGoal', this.goal,
      httpOptions).subscribe(
      result => {
        console.log(result);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.viewProfile();
    }, 1500);
    setTimeout(() => {
      this.subject.getWaste(this.itemsModel, this.userModel.goal);
    }, 1500);
  }


  getWReport() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };


    function getColor(result: StringObj) {
      if (result.myString.lastIndexOf('\n') > 0) {
        if (result.myString.substring(result.myString.lastIndexOf('\n') + 1) === 'green') {
          return 1;
        } else if (result.myString.substring(result.myString.lastIndexOf('\n') + 1) === 'red') {
          return 2;
        } else {
          return 0;
        }
      }
    }

    this.http.get<StringObj>('http://localhost:8080/users/getWeeklyReport',
      httpOptions).subscribe(result => {
        this.color = getColor(result);
        console.log(this.color);
        if (result.myString.lastIndexOf('\n') > 0) {
          this.stringObj.myString = result.myString.substring(0, result.myString.lastIndexOf('\n'));
        } else {
          this.stringObj.myString = 'report error';
        }
        console.table(this.stringObj);
      },
      error => console.log(error));
  }

  getMReport() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };

    function getColor(result: StringObj) {
      if (result.myString.lastIndexOf('\n') > 0) {
        if (result.myString.substring(result.myString.lastIndexOf('\n') + 1) === 'green') {
          return 1;
        } else if (result.myString.substring(result.myString.lastIndexOf('\n') + 1) === 'red') {
          return 2;
        } else {
          return 0;
        }
      }
    }

    this.http.get<StringObj>('http://localhost:8080/users/getMonthlyReport',
      httpOptions).subscribe(result => {
        this.color = getColor(result);
        console.log(this.color);
        if (result.myString.lastIndexOf('\n') > 0) {
          this.stringObj.myString = result.myString.substring(0, result.myString.lastIndexOf('\n'));
        } else {
          this.stringObj.myString = 'report error';
        }
        console.table(this.stringObj);
      },
      error => console.log(error));
  }
}
