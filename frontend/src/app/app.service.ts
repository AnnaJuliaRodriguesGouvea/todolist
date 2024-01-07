import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  baseUrl = 'http://localhost:8080/api/tasks';
  constructor(private http: HttpClient) {

  }

  getTaskList() {
    return this.http.get<Task[]>(`${this.baseUrl}`);
  }

  addTask(postData: Task) {
    return this.http.post(`${this.baseUrl}`, postData);
  }

  updateTask(postData: Task) {
    return this.http.put(`${this.baseUrl}/${postData.id}`, postData);
  }

  workingTask(id: Task['id']) {
    return this.http.put(`${this.baseUrl}/working/${id}`, null);
  }

  completedTask(id: Task['id']) {
    return this.http.put(`${this.baseUrl}/completed/${id}`, null);
  }

  deleteTask(id: Task['id']) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
