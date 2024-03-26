import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutercommentaireComponent } from './ajoutercommentaire.component';

describe('AjoutercommentaireComponent', () => {
  let component: AjoutercommentaireComponent;
  let fixture: ComponentFixture<AjoutercommentaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjoutercommentaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AjoutercommentaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
