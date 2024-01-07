import { Component, OnInit, ViewChild } from '@angular/core';
import { Task } from './task';
import { AppService } from './app.service';
import {PriorityEnum} from "./model/priority-enum";
import {MatDialog} from "@angular/material/dialog";
import {StatusEnum} from "./model/status-enum";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild('todoTask') todoTask: any;

  task = '';
  tasks: Task[] = [];
  priority = '';
  priorityOptions = [
    { value: PriorityEnum.LOW },
    { value: PriorityEnum.MEDIUM },
    { value: PriorityEnum.HIGH }
  ];
  statusNew = StatusEnum.NEW
  statusWorking = StatusEnum.WORKING
  statusCompleted = StatusEnum.COMPLETED

  selectedId?: number
  modalTaskVisible = false
  taskModal = '';
  priorityModal = ""

  constructor(private appService: AppService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getList();
  }

  getList() {
    this.appService
      .getTaskList()
      .subscribe(
      response => {
          this.tasks = response;
        }
      )
  }

  addTask() {
    this.appService
      .addTask({ name: this.task, priorityEnum: this.priority })
      .subscribe(
      response => {
          this.todoTask.reset();
          this.getList();
        }
      )
  }

  updateTask(e: unknown) {
    this.appService
      .updateTask({ id: this.selectedId, name: this.taskModal, priorityEnum: this.priorityModal })
      .subscribe( response => {
          this.modalTaskVisible = false;
          this.getList();
        }
      )
  }

  workingTask(e: unknown,  id: Task['id']) {
    this.appService
      .workingTask(id)
      .subscribe( response => {
          this.getList();
        }
      )
  }

  completedTask(e: unknown,  id: Task['id']) {
    this.appService
      .completedTask(id)
      .subscribe( response => {
          this.getList();
        }
      )
  }

  deleteTask(e: unknown, id: Task['id']) {
    this.appService
      .deleteTask(id)
      .subscribe(
      response => this.getList()
      )
  }

  showModal(e: Event, task: Task) {
    e?.stopPropagation()
    this.modalTaskVisible = true;
    this.selectedId = task?.id;
    this.taskModal = task.name;
    this.priorityModal = task.priorityEnum;
  }

  closeModal(e: Event) {
    e?.stopPropagation()
    this.modalTaskVisible = false;
  }

  getPrioritySeverity(priority: string): string {
    switch (priority) {
      case 'HIGH':
        return 'danger';
      case 'MEDIUM':
        return 'warning';
      case 'LOW':
        return 'success';
      default:
        return 'info';
    }
  }

  getStatusSeverity(status: string): string {
    switch (status) {
      case 'COMPLETED':
        return 'success';
      case 'WORKING':
        return 'warning';
      case 'NEW':
        return 'info';
      default:
        return 'default';
    }
  }
}
