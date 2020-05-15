import {Observer} from './Observer';
import {ConcreteSubject} from './ConcreteSubject';

export class WasteObserver implements Observer {
  waste = false;

  public update(subject: ConcreteSubject): void {
    if (subject.calWasted > 0) {
      this.waste = true;
    } else {
      this.waste = false;
    }
  }
}
