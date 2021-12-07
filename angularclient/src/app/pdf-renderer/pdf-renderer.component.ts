import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-pdf-renderer',
  templateUrl: './pdf-renderer.component.html',
  styleUrls: ['./pdf-renderer.component.scss'],
})
export class PdfRendererComponent implements OnInit {
  contentId: any;
  pdfUrl = '';
  controllerSrc: any;

  constructor(
    private _activatedRoute: ActivatedRoute,
    private _appService: AppService,
    private dom: DomSanitizer
  ) {
    this._activatedRoute.paramMap.subscribe((pathParams) => {
      this.contentId = pathParams.get('id');
    });
  }

  ngOnInit(): void {
    this._appService.getContent(this.contentId).subscribe((response) => {
      this.pdfUrl = 'http://localhost:8080/assets/' + response?.launchUrl;
      this.controllerSrc = this.dom.bypassSecurityTrustResourceUrl(this.pdfUrl);
    });
  }
}
