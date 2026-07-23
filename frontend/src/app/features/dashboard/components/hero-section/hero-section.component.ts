import { Component } from '@angular/core';

@Component({
  selector: 'mv-hero-section',
  standalone: true,
  templateUrl: './hero-section.component.html',
  styleUrl: './hero-section.component.scss'
})
export class HeroSectionComponent {

  greeting = 'Boa noite, Ruan';

  message = 'Continue construindo seu segundo cérebro.';

}