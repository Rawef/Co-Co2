import { Component } from '@angular/core';
import { ServiceService } from '../../services/service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { trigger, state, style, animate, transition } from '@angular/animations';
import {  OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  

})
export class LoginComponent   {

  
 
 @Input() password: string= '';
  
  nom: string ="";
  email: string ="";
  passwordStrength: string = '';
  strengthWidth: string = '0%';
  strengthText: string = '';
  strengthColor: string = ''; // Add this line


  
  constructor(private http: HttpClient ,private router: Router,private service: ServiceService )
  {
  }

  login() {
    let bodyData = {
      email: this.email,
      password: this.password,
    };
  
    this.service.loginUser(bodyData).subscribe(
      (user: any) => {
        // Handle successful login
        console.log('Logged-in User Details:', user);
        this.service.setLoggedInUser(user);
  
        // Redirect to preferences page
        this.router.navigateByUrl('preferances');
      },
      (error) => {
        // Handle errors here
        if (error.status === 404) {
          alert("Email does not exist");
        } else {
          alert("Incorrect Email and Password not match");
        }
      }
    );
  }
  

  save() {
    // Check if the password is strong before proceeding with registration
    if (this.isPasswordStrong()) {
      let bodyData = {
        "nom": this.nom,
        "email": this.email,
        "password": this.password
      };

      // Make the HTTP request for registration
      this.http.post("http://localhost:8089/save", bodyData, { responseType: 'text' }).subscribe(
        (resultData: any) => {
          console.log(resultData);
          alert("User Registered Successfully");
        },
        (error) => {
          console.error("Error during registration:", error);
          alert("Registration failed. Please try again.");
        }
      );
    } else {
      alert("Password is not strong enough. Please choose a stronger password.");
    }
  }

  isPasswordStrong(): boolean {
    // Add your password strength criteria here
    // For example, you can check if the meter status is "strong"
    return document.getElementById('meter-status')?.innerText === 'strong';
  }


  checkStrength(password: string) {
    let meterBar = $("#meter").find("#meter-bar");
    let meterStatus = $("#meter-text").find("#meter-status");
    let strength = 0;

    if (password.length < 5) {
      meterBar.css({
        "background": "#6B778D",
        "width": "10%"
      });
      meterStatus.css("color", "#6B778D");
      meterStatus.text("too short");
    }
    if (password.length > 5) strength += 1;
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1;
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1;
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1;

    if (strength < 2) {
      meterBar.css({
        "background": "#ff0000",
        "width": "25%"
      });
      meterStatus.css("color", "#ff0000");
      meterStatus.text("weak");
    } else if (strength == 2) {
      meterBar.css({
        "background": "#00BCD4",
        "width": "75%"
      });
      meterStatus.css("color", "#00BCD4");
      meterStatus.text("good");
    } else {
      meterBar.css({
        "background": "#4CAF50",
        "width": "100%"
      });
      meterStatus.css("color", "#4CAF50");
      meterStatus.text("strong");
    }
  
  

}


}
