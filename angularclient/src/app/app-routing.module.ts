import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { HtmlRendererComponent } from './html-renderer/html-renderer.component';
import { LoginComponent } from './login/login.component';
import { PdfRendererComponent } from './pdf-renderer/pdf-renderer.component';
import { VideoRendererComponent } from './video-renderer/video-renderer.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'login',
      },
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'html/:id',
        component: HtmlRendererComponent,
      },
      {
        path: 'video/:id',
        component: VideoRendererComponent,
      },
      {
        path: 'pdf/:id',
        component: PdfRendererComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
