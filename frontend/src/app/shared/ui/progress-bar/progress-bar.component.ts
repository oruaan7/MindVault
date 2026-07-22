import { Component, Input } from '@angular/core';

@Component({
    selector: 'mv-progress-bar',
    standalone: true,
    templateUrl: './progress-bar.component.html',
    styleUrl: './progress-bar.component.scss'
})
export class ProgressBarComponent {

    @Input({ required: true })
    value!: number;

}