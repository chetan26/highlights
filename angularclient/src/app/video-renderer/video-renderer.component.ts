import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContentDetails, HighlightData } from '../html-data';
import { AppService } from '../app.service';

@Component({
  selector: 'app-video-renderer',
  templateUrl: './video-renderer.component.html',
  styleUrls: ['./video-renderer.component.scss']
})
export class VideoRendererComponent implements OnInit {
data: any;
buttonText = "START";
bookMarkStartTime :any;
bookMarkStopTime :any;
contentId:any;
startDisabled=false;
stopDisabled=true;
launchUrl='';
note='';
@ViewChild('highlightContent', { static: false }) highlightContentElement:
| ElementRef
| undefined;

@ViewChild('noteDiv', { static: false }) noteElement: ElementRef | undefined;
constructor(
  private _appService: AppService,
  private _activatedRoute: ActivatedRoute,
  private _renderer: Renderer2
) {
  this._activatedRoute.paramMap.subscribe((pathParams) => {
    this.contentId = pathParams.get('id');
  });
}

ngOnInit(): void {
  this._appService.getContent(this.contentId).subscribe((response) => {
    this.data = response;
   this.launchUrl= "http://localhost:8080/assets/"+this.data.launchUrl;
    console.log('url of video is '+this.launchUrl);
  });
}

  convertTime(time:any):string {
    if(time<3600){
     return new Date(time * 1000).toISOString().substr(14, 5);
     }else{
      return new Date(time * 1000).toISOString().substr(11, 8);
     }

  }

  hideTheDiv(): void {
    if (this.highlightContentElement?.nativeElement) {
      this.highlightContentElement.nativeElement.hidden = true;
    }
  }
  bookmarkStartTime():void{
    this.bookMarkStartTime = document.getElementsByTagName("video")[0].currentTime;
    this.startDisabled=true;
    this.stopDisabled=false;
    }

    showSaveDialog():void{
      if (this.highlightContentElement?.nativeElement) {
          this.highlightContentElement.nativeElement.hidden = false;
        }
    }
    bookmarkStopTime():void{
      this.bookMarkStopTime = document.getElementsByTagName("video")[0].currentTime;
      var from = this.convertTime(this.bookMarkStartTime);
      var to = this.convertTime(this.bookMarkStopTime);

      console.log("START TIME IS "+from +"END TIME IS "+to);
      const data = {
        contentId: this.contentId,
        context: {
          note: this.note,
        },
        location: {
          ancestorId: 'TODO',
          offset: 0,
        },
        source: 'TODO',
        text: 'video bookmark',
        trim: {
          from: from,
          to: to ,
        },
        type: 'video'
      };
      this._appService.saveHighLightedText(data).subscribe((response) => {
        this.startDisabled=false;
        this.stopDisabled=true;
      });
    }
      seekTime(): void{
        document.getElementsByTagName("video")[0].currentTime = 250;
      }

      // getContentThumbnail(data: ContentDetails): string {
      //   //return '/assets/contents/' + data.id + '/' + data.imgUrl;
      //   //TODO: Ask Pankaj for help changing this
      //   return 'http://localhost:8080/assets/' + data.id + '/' + data.imgUrl;
      // }
  }


