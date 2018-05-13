import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from './../../app.component';
import { Component, OnInit, Input, Host } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AlertService } from 'ngx-alerts';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  @Input() type : String ;
  type2 : String ;
  private url = "/logoutService";

  constructor(private http: HttpClient, private alertService: AlertService , private translate: TranslateService , @Host() private app : AppComponent , private router : Router) 
  {
    translate.setDefaultLang('en');

    if (sessionStorage.length > 0) 
    {
      // We have items
      this.type = sessionStorage.getItem("type");
    }
     else
      {
      // No items
      this.type = "null";
    }

    if(sessionStorage.getItem("lang"))
    {
      this.switchLanguage(sessionStorage.getItem("lang"));
    }

  }

  switchLanguage(language: string) {
    sessionStorage.setItem("lang" , language);
    this.translate.use(language);
  }

  spanclick() : void 
  {
    alert("aa");
  }

  logout()
  {
    this.http.get(this.url).subscribe(response => 
      {
        this.app.type = null ;
        sessionStorage.clear();
        this.router.navigateByUrl('/login')
        this.alertService.success('logged out successfully ');
    });

  }

}
