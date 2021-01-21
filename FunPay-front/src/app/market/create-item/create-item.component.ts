import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html'
})
export class CreateItemComponent {
  constructor(
    public dialogRef: MatDialogRef<CreateItemComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
}
