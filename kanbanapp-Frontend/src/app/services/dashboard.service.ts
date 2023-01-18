import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private dashboardURL = "http://localhost:9001/kanban/v1/";
  private findProjectByEmailURL ="http://localhost:9001/kanban/v1/projectnames/";
  private findProjectByNameURL="http://localhost:9001/kanban/v1/findproject/";
  private addColumnURL ="http://localhost:9001/kanban/v1/addcol/";
  private deleteColumnURL ="http://localhost:9001/kanban/v1/deleteCol/";
  private addTaskURL ="http://localhost:9001/kanban/v1/addtask/";
  private deleteTaskURL =" http://localhost:9001/kanban/v1/deletetask/"
  private editTaskURL ="http://localhost:9001/kanban/v1/edittask/"
  private moveTaskURL = " http://localhost:9001/kanban/v1/movetask/"
  private showUserInfoURL ="http://localhost:9001/kanban/v2/emplist"
  private addProjectURL = "http://localhost:9001/kanban/v1/save";
  private deleteProjectURL ="http://localhost:9001/kanban/v1/deleteProject/";
  private getUserDataURL ="http://localhost:9001/kanban/v2/searchbyemailid/"
  private inviteUserEmailURL ="http://localhost:9001/kanban/v2/inviteemail/"
  private addMEmberURL ="http://localhost:9001/kanban/v1/addmember/"
  private removeMemberURL ="http://localhost:9001/kanban/v1/deletemember/"

  constructor(private http: HttpClient) { }

  getAllProjects(): Observable<any> {
    return this.http.get(`${this.dashboardURL}getallprojects`)
  }

  addNewProject(data:any){
    return this.http.post(`${this.addProjectURL}`, data, );
  }

  deleteProject(projectname:any){
    return this.http.delete(this.deleteProjectURL+projectname, {responseType: 'text'});
  }

  getProjectByEmail(emailid:any): Observable<any>{
    return this.http.get(this.findProjectByEmailURL+emailid);
  }

  getProjectByName(projectName: any){
    return this.http.get(this.findProjectByNameURL+projectName);
  }

  addColumn(projectName: any, columnName:any){
    console.log(this.addColumnURL+projectName+'/'+columnName)
    return this.http.get(this.addColumnURL+projectName+'/'+columnName);
  }

  deleteColumn(projectName: any, columnName:any){
    return this.http.delete(this.deleteColumnURL+projectName+'/'+columnName, {responseType: 'text'})
  }

  

  getUserInfo(){
    return this.http.get(this.showUserInfoURL);
  }

  getUserdata(emailid: any){
    return this.http.get(this.getUserDataURL+emailid);
  }
  addTask(projectName: any, columnName:any, data:any){
    return this.http.post(this.addTaskURL+projectName+'/'+columnName, data);
  }

  deleteTask(projectName: any, columnName:any, taskbody:any){
    return this.http.post(this.deleteTaskURL+projectName+'/'+columnName, taskbody ,{responseType: 'text'})
  }

  updateTaskInColumn(projectName: any, prevcolumnName:any, nextcolumnname:any, taskbody:any){
    console.log(nextcolumnname)
    return this.http.post(this.moveTaskURL+projectName+'/'+prevcolumnName+'/'+nextcolumnname, taskbody,{responseType: 'text'})
  
  }

  editTask(projectName:any, columnName:any, taskBody:any){
    return this.http.post(this.editTaskURL+projectName+'/'+columnName, taskBody)
  }


  inviteUserEmail(emailid:any){
    return this.http.get(this.inviteUserEmailURL+emailid,{responseType: 'text'})
  }

  addMember(projectName:any, emailid:any){
    return this.http.get(this.addMEmberURL+projectName+'/'+emailid)
  }

  removeMember(projectName:any, emailid:any){
    return this.http.get(this.removeMemberURL+projectName+'/'+emailid)
  }

}
