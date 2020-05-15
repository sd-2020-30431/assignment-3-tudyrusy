import {ItemModel1} from './item1.model';
import {Subject} from './Subject';
import {Observer} from './Observer';


export class ConcreteSubject implements Subject {

  public calWasted: number;
  public s;
  private observers: Observer[] = [];


  public attach(observer: Observer): void {
    const isExist = this.observers.includes(observer);
    if (isExist) {
      return console.log('Subject: Observer has been attached already.');
    }

    console.log('Subject: Attached an observer.');
    this.observers.push(observer);
  }

  public detach(observer: Observer): void {
    const observerIndex = this.observers.indexOf(observer);
    if (observerIndex === -1) {
      return console.log('Subject: Nonexistent observer.');
    }

    this.observers.splice(observerIndex, 1);
    console.log('Subject: Detached an observer.');
  }


  public notify(): void {
    console.log('Subject: Notifying observers...');
    for (const observer of this.observers) {
      observer.update(this);
    }
  }

  public getWaste(itemsModel: ItemModel1[], goal): void {
    this.s = 0;
    for (const i of itemsModel) {
      if (i.consumptionDate === 'N/As') {
        this.s += i.perDay;
      }
    }
    this.calWasted = this.s - goal;

    console.log(`Subject: My state has just changed to: ${this.calWasted}`);
    this.notify();
  }
}
