import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private title = 'app';
  
  type : any  ;


  ngOnInit(): void 
  {
        //setting the current type from the session 
        if(sessionStorage.getItem('type'))
        {
          this.type = sessionStorage.getItem('type');
        }
  }
  constructor(private translate: TranslateService)
   {
     // setting default language 
    translate.setDefaultLang('en');


  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }
  
}
