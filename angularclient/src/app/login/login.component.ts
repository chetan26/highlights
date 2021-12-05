import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  credentials = { userName: '', password: '' };

  constructor(private _appService: AppService, private router: Router) {}

  ngOnInit(): void {}

  login() {
    this._appService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/home');
    });
    return false;
  }
}
