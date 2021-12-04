import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfRendererComponent } from './pdf-renderer.component';

describe('PdfRendererComponent', () => {
  let component: PdfRendererComponent;
  let fixture: ComponentFixture<PdfRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PdfRendererComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PdfRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
