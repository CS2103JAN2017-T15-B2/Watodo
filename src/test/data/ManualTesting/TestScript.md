# Team T15-B2 TestScript

### 1. Loading sample data
to loading 50+ sample tasks from storage folder, make sure to copy SampleData.xml from `[project root]\src\test\data\ManualTesting\TestScript.md` to the same directory to this app’s jar file. After which, enter the following command:

`change_path SampleData.xml`

the following prompt should appear in the feedback display: 
> Changed data file location. You need to restart the app for changes to take effect.

Close the window and reopen this app’s jar file. This task list panel should be populated by around 50 tasks as sample data.

Next, in order to have a better visualization, we should create a new storage file to start with a clean slate task list panel, this can be done by entering the following: 

`change_path data/StorageForTesting.xml`

Upon restarting, a new folder will be created, containing `StorageForTesting.xml`, and only a sample task should be listed.

### 2. HelpCommand

Enter command<br>
`help`<br>
A browser window with User Guide instructions will be popped out.

### 3. AddCommand 

The quickest way to add a task, is just keying the command word "add", followed by the task name.<br>
`add get haircut`<br>

The feedback display panel should show
> New task added: get haircut StartTime: - endTime: - Priority: low Tags:  

To add an event task, we can fill up both the start date-time and end date-time. We can also set the priority as high and lastly, we can attach a tag "hackathon". Notice that we are using a different format of date here.
`add Attend CCA meeting from/today 3.00pm to/today 5.00pm p/high t/hackathon`

This feedback panel should now show:
> New task added: Attend CCA meeting StartTime: 09/04/2017 15:00 endTime: 09/04/2017 17:00 Priority: high Tags: [hackathon]

Now the remaining commands are to test that other different formats of date-time can work.<br>
`add call Darius to/2017-04-07 3.00pm`

`add call Wen Tong to/7 Apr 2017 3.00pm t/lunch`

`add call Min Hui to/tmr 3.00pm`


*Notice that if we key in the following command:
`add skype with Oguz from/today 3.00pm`
The following error message will be shown
> Invalid task: Event without an end time. Please fill up end time<br>


### 4. EditCommand

To change the name of a task, simply enter:
`edit 2 get a stylish haircut`

Feedback:
> Edited Task: get a stylish haircut StartTime: - endTime: 08/09/2017 09:00 Priority: low Tags:

Next we can fill up the empty end-time of a task by editing the endTime field, following the prefix `to/`. Notice that once the endTime is set, this task now becomes a deadline task, as it changes color from blue to yellow.<br>
`edit 2 to/08/09/2017 09:00`

Feedback:
> Edited Task: get a stylish haircut StartTime: - endTime: 08/09/2017 09:00 Priority: low Tags: 

Also, edit command can be used to set a date-time component empty, too.
`edit 3 from/`<br>
The above command will cancel the start time of task 3, and the task atuomatically becomes a deadline task, as it changes from green to yellow. 

In general, the format of edit is as follows:
`Parameters: edit [INDEX] [TASK-NAME] from/{[DD/MM/YYYY] [HH:MM]} to/{[DD/MM/YYYY] [HH:MM]} p/[high/med/low] t/[KEYWORD]...`

* The only acceptable input date-time format for edit command is `dd/mm/yyyy HH:MM`, other formats are not recognizable.

* all fields are optional, and 

* Except that [TASKNAME] must be in the first field, other field with a prefix can be type in any order.
For example: 
`edit 4 t/sometag to/13/03/2017 11:00 p/med  from/13/03/2017 19:00`

### 4. Find Command

Enter the command<br>
`find Darius`
> 1 tasks listed!<br>

The task panel will be left with only 1 task that contains `Darius`.

Enter command<br>
`find min hui`
> 1 tasks listed!<br>

The task panel will be left with 1 task that contains `Min Hui`, notice that the search is case insensitive.

Enter command<br>
`find pokemon`
> 0 tasks listed!

Task Panel will show no task, as there is no task with the name `pokemon`

### 5. List Command

Enter<br>
`list`
> Listed all tasks

This lists out all saved tasks.

### 6. Delete Command

Enter<br>
`delete 1`
> Deleted Task: Initial Sample Task StartTime: 17/02/2017 10:00 endTime: 17/02/2017 23:59 Priority: high Tags: [friends]

The task with the index 1 has now been deleted.

### 7. Mark Command

Enter<br>
`mark 1 completed`
> Marked Task: {Task details}

This changes the status of the task 1 as complete, this is indicated by the red-cross changed to green tick, and the task card background color changed to gray.

Enter<br>
`mark 1 incomplete`
> Marked Task: {Task details}

This changes the status of the task 1 to incomplete, this is indicated by the green tick changed to red-cross, and the task card background color changed from gray to green.

### 8. Clear Command

Enter<br>
`clear`
> Task manager has been cleared!

This command deletes all tasks in the task manager.

### 9. Undo Command 

Enter, in order
`undo`
> Your previous action has been undone.

As we entered a clear command in the previous section, if we enter an undo, the deleted tasks will be restored.

Currently, `undo` is can be used to undo `mark`, `delete`, `add` and `clear` commands only.