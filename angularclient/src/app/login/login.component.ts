import { Component, OnDestroy, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  credentials = { userName: '', password: '' };

  constructor(private _appService: AppService, private router: Router) {}

  ngOnInit(): void {
    document.body.className = 'selector';
  }

  ngOnDestroy(): void {
    document.body.className = '';
  }

  login() {
    this._appService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/home');
    });
    return false;
  }
}
