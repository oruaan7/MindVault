import { Component, Input } from '@angular/core';

import { ProgressBarComponent } from '../../../../shared/ui/progress-bar/progress-bar.component';

import { GoalProgress } from '../../models/goal-progress.model';

@Component({
    selector: 'mv-goal-progress',
    standalone: true,
    imports: [
        ProgressBarComponent
    ],
    templateUrl: './goal-progress.component.html',
    styleUrl: './goal-progress.component.scss'
})
export class GoalProgressComponent {

    @Input({ required: true })

    goals!: GoalProgress[];

}