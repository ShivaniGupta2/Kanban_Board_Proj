import { Component, ComponentFactoryResolver } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, reduce, shareReplay } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';

import { DashboardService } from 'src/app/services/dashboard.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Project } from 'src/app/model/Project.model';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Tasks } from 'src/app/model/Tasks.model';

import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import * as alertify from 'alertifyjs'
import { ComponentType } from '@angular/cdk/portal';
import { User } from 'src/app/model/User.model';
import { Column } from 'src/app/model/Column.model';



@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private dialog: MatDialog, private dashboardservices: DashboardService, private auth: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  projectsName: any = []
  ngOnInit(): void {
    this.dashboardservices.getProjectByEmail(this.auth.getEmail()).subscribe(response => {
      this.projectsName = response;
      console.log(response)
    })
    this.Addnewrow();
    this.Addnewcolrow();
    this.getUserData()
  }

  frontpage: boolean = true;
  disableFrontpage() {
    this.frontpage = false;
  }

  bgColumnColor: String = '#e9ecef';
  changeColumnColor() {
    this.bgColumnColor = '#' + (Math.random() * 0xFFFFFF << 0).toString(16);
  }


  selectBgcolor: any = {
    name: 'Blue',
    color: '#184e77'

  }


  data = [{
    name: 'Blue',
    color: '#184e77'
  }, {
    name: 'green',
    color: '#008000'
  },
   {
    name: 'pink',
    color: 'pink'
  }, 
  {
    name: 'Aquamarine',
    color: '#7FFFD4'
  },
  {
    name: 'Teal Green',
    color: '#00827F'
  }
]

  compareObjects(o1: any, o2: any): boolean {
    return o1.color === o2.color
  }










  userEmpname = new User;
  userData() {
    this.dashboardservices.getUserdata(this.auth.getEmail()).subscribe((response: any) => {
      this.userEmpname = response;
    })
  }




  connectedTo: any = [];
  extractColumn(projectdata: Project | any) {
    for (let columns of projectdata.columnList) {
      console.log(columns.colName)
      this.connectedTo.push(columns.colName);
    }
  }

  membername: any = []
  memberemail: any = []
  extractMembers(projectdata: Project | any) {
    if (this.memberemail.length == 0) {
      for (let member of projectdata.members) {
        this.memberemail.push(member.emailid);
        this.membername.push(member.name);
      }
    }
  }




  onTaskDrop(event: CdkDragDrop<any>, nextCol: any) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      // console.log(event.previousContainer.data)
      // console.log(event.container.data)
      // console.log(event.previousContainer.data.indexOf(event.item.data))
      // console.log(event.currentIndex)
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousContainer.data.indexOf(event.item.data),
        event.currentIndex);
    }
    // console.log(nextCol)
    // console.log(this.prevColumnName)
    // console.log(this.taskbody)
    this.dashboardservices.updateTaskInColumn(this.currentProject, this.prevColumnName, nextCol, this.taskbody).subscribe(response => {
      console.log(response)
      this.getBoard(this.currentProject)
    })
  }

  prevColumnName: any
  taskbody: any
  prevCol(columnName: any, task: any) {
    this.prevColumnName = columnName;
    this.taskbody = task
  }











  logout() {
    this.auth.clear();
    this.router.navigate(['home'])
  }






  selected: any
  update(e: any) {
    this.selected = e.target.value
  }




  username: any = this.auth.getEmail();
  project = new Project;
  projectinfo: Project | undefined;
  currentProject: any | undefined;
  setCurrentProject(projectName: string) {
    this.currentProject = projectName;
  }


  currentauthorname: any;
  boardname: any;
  getBoard(boardname: String) {
    console.log(boardname);
    this.dashboardservices.getProjectByName(boardname).subscribe((response: any) => {
      this.project = response
      console.log(this.project)
      this.boardname = this.project.projectname
      this.currentauthorname = this.project.author
      this.projectinfo = response;
      this.extractColumn(this.project)
      this.extractMembers(this.project)
      console.log("hello world")
    })
  }



  // column form data -----------------------------

  columnform = new FormGroup({
    colName: new FormControl('', Validators.required),
  });
  get colName() {
    return this.columnform.get('colName');
  }


  deleteColumn() {
    alertify.confirm('Are you sure you want to delete the column ?', () => {
      this.dashboardservices.deleteColumn(this.currentProject, this.currentColumn).subscribe(response => {
        alertify.success(response)
        this.getBoard(this.currentProject)
      }, error => {
        alertify.error("Something went wrong, please try again !!!")
      })
    })
  }


  insertNewColumn() {
    console.log(this.colName?.value)
    if (this.columnform.valid) {
      this.dashboardservices.addColumn(this.currentProject, this.colName?.value).subscribe(response => {
        alertify.success("Created Successfully !!!")
        this.getBoard(this.currentProject)
      })
    }

  }


  disableAddColumnBtn: boolean = false;
  enableAddColumnBtn() {
    this.disableAddColumnBtn = true;
  }

  openNewColumnFormDialogue(templateRef1: any) {
    let dialogRef = this.dialog.open(templateRef1, {
      width: '200px'
    })
  }


  openColumnDeleteDialogue(templateRef2: any) {
    let dialogRef = this.dialog.open(templateRef2, {
      width: '500px'
    })
  }









  // add task form data -----------------------------

  taskform = new FormGroup({
    heading: new FormControl('', Validators.required),
    description: new FormControl('', [Validators.required, Validators.pattern("^.{1,85}$")]),
    deadline: new FormControl('', Validators.required),
    priority: new FormControl('', Validators.required),
    assignedto: new FormControl('', Validators.required)
  });

  get f() {
    return this.taskform.controls;
  }



  openNewTaskFormDialogue(templateRef: any) {
    let dialogRef = this.dialog.open(templateRef, {
      width: '500px'
    })
  }
  currentColumn: any | undefined;
  getCol(colName: any) {
    this.currentColumn = colName;
    console.log(this.currentColumn);
  }

  insertNewTask() {
    console.log(this.taskform.value)
    if (this.taskform.valid) {
      this.dashboardservices.addTask(this.currentProject, this.currentColumn, this.taskform.value).subscribe(response => {
        alertify.success("Created Successfully !!!")
        this.getBoard(this.currentProject)
      }, error => {
        alertify.error("Something went wrong, please try again !!!")
      })
    }
  }

  deleteTask(taskbody: any, col: any) {
    alertify.confirm('Are you sure you want to delete the task ?', () => {
      this.dashboardservices.deleteTask(this.currentProject, col, taskbody).subscribe(response => {
        alertify.success("Removed Successfully")
        this.getBoard(this.currentProject)
      }, error => {
        alertify.error("Something went wrong, please try again !!!")
      })
    })

  }



  openTaskDeleteDialogue(templateRef3: any) {
    let dialogRef = this.dialog.open(templateRef3, {
      width: '500px'
    })
  }






  // update task form data

  taskBody: any;
  showTaskBody(task: any) {
    this.taskBody = task

  }

  getHeading() {
    return this.taskBody?.heading
  }

  updatetaskform = new FormGroup({
    heading: new FormControl(''),
    description: new FormControl('', [Validators.required, Validators.pattern("^.{1,85}$")]),
    deadline: new FormControl('', Validators.required),
    priority: new FormControl('', Validators.required),
    assignedto: new FormControl('', Validators.required)
  });



  get updateControls() {
    return this.updatetaskform.controls;
  }



  openUpdateTaskFormDialogue(templateRef5: any) {
    let dialogRef = this.dialog.open(templateRef5, {
      width: '500px'
    })
  }



  editExistingTask() {
    this.updatetaskform.controls['heading'].setValue(this.getHeading())
    console.log(this.updatetaskform.value)
    this.dashboardservices.editTask(this.currentProject, this.currentColumn, this.updatetaskform.value).subscribe(response => {
      alertify.success("Updated Successfully")
      this.getBoard(this.currentProject)
    }, error => {
      console.log(error)
      alertify.error("Something went wrong, please try again !!!")
    })
  }






  // add New Project Form ---------------------------------

  title = 'FormArray';
  items!: FormArray;
  columnitems!: FormArray;
  reactform = new FormGroup({
    projectname: new FormControl('', Validators.required),
    author: new FormControl(this.auth.getEmail()),
    members: new FormArray([]),
    columnList: new FormArray([])
  });

  get pname() {
    return this.reactform.get('projectname')
  }


  ProceedSave() {
    this.dashboardservices.addNewProject(this.reactform.value).subscribe(response => {
      console.log(response);
      alertify.success("Created Successfully !!!")
      this.dashboardservices.getProjectByEmail(this.auth.getEmail()).subscribe(response => {
        this.projectsName = response;
      })
    }, error => {
      console.log(error)
      alertify.error("board already exist")
    })
  }

 

  deleteProject() {
    alertify.confirm('Are you sure you want to delete the project ?', () => {
      if (this.auth.getEmail() === this.currentauthorname) {
        this.dashboardservices.deleteProject(this.currentProject).subscribe(response => {
          alertify.success(response)
          this.dashboardservices.getProjectByEmail(this.auth.getEmail()).subscribe(response => {
            this.projectsName = response;
          })
          this.router.navigate(["dashboard"])
        })
      }
      else {
        alertify.error("Only author can delete board")
      }
    })
  }


  addMember(emailid:any){
    alertify.confirm('Do you wish to add '+emailid, () => {
       this.dashboardservices.addMember(this.currentProject,emailid).subscribe(response => {
        alertify.success(emailid+" added successfully")
        this.getBoard(this.currentProject)
       }, error => {
        console.log(error)
       })
    })
  }

  removeMember(emailid:any){
    if (this.auth.getEmail() === this.currentauthorname){
      alertify.confirm('Do you wish to remove '+emailid, () => {
        this.dashboardservices.removeMember(this.currentProject,emailid).subscribe(response => {
         alertify.success(emailid+" removed successfully")
         this.getBoard(this.currentProject)
        }, error => {
         console.log(error)
        })
     })
    }
    else {
      alertify.error("Only author can remove member")
    }
    
  }




  Addnewrow() {
    this.items = this.reactform.get("members") as FormArray;
    this.items.push(this.Genrow())
  }
  Removeitem(index: any) {
    this.items = this.reactform.get("members") as FormArray;
    this.items.removeAt(index)
  }

  Addnewcolrow() {
    this.columnitems = this.reactform.get("columnList") as FormArray;
    this.columnitems.push(this.GenColrow())
  }
  Removecolitem(index: any) {
    this.columnitems = this.reactform.get("columnList") as FormArray;
    this.columnitems.removeAt(index)
  }

  get members() {
    return this.reactform.get("members") as FormArray;
  }

  get columnList() {
    return this.reactform.get("columnList") as FormArray;
  }

  Genrow(): FormGroup {
    return new FormGroup({
      name: new FormControl(),
      emailid: new FormControl(this.auth.getEmail()),
    });
  }

  GenColrow(): FormGroup {
    return new FormGroup({
      colName: new FormControl('', Validators.required),
      tasksList: new FormArray([])
    });
  }


  openNewProjectFormDialog(templateRef2: any) {
    let dialogRef = this.dialog.open(templateRef2, {
      width: '400px'
    })
  }

  user: any = []
  getUserData() {
    this.dashboardservices.getUserInfo().subscribe((response: any) => {
      this.user = response
      console.log(this.user)
    })
  }





}
