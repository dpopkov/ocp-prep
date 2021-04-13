# Examples from "OCP Java SE 11 Programmer II Study Guide"

__All the code in this repository is just for illustrating purposes!__.

## Chapter 7 - Concurrency

### Creating Threads with the Concurrency API
* [Single-Thread Executor](src/main/java/learn/ocp/progr2/ch07concurrency/ZooInfo.java)

### Waiting for Results
* Use `submit(Runnable)` to wait for result: [CheckResults](src/main/java/learn/ocp/progr2/ch07concurrency/CheckResults.java)
* Use `submit(Callable)` to wait for result: [AddData](src/main/java/learn/ocp/progr2/ch07concurrency/AddData.java)
* Wait for service termination: [WaitForAll](src/main/java/learn/ocp/progr2/ch07concurrency/WaitForAll.java)
* Submit Task Collections: [InvokeAll](src/main/java/learn/ocp/progr2/ch07concurrency/InvokeAll.java)
* Wait for first successful: [InvokeAny](src/main/java/learn/ocp/progr2/ch07concurrency/InvokeAny.java)

### Writing Thread-Safe Code
* Race condition: [SheepManagerRaced](src/main/java/learn/ocp/progr2/ch07concurrency/SheepManagerRaced.java)
* Atomic: [SheepManagerAtomic](src/main/java/learn/ocp/progr2/ch07concurrency/SheepManagerAtomic.java)
* Synchronized: [SheepManagerSynch](src/main/java/learn/ocp/progr2/ch07concurrency/SheepManagerSynch.java)
* ReentrantLock with tryLock: [SheepManagerTryLock](src/main/java/learn/ocp/progr2/ch07concurrency/SheepManagerTryLock.java)
* [DeadLockExample](src/main/java/learn/ocp/progr2/ch07concurrency/DeadLockExample.java)

### Orchestrating Tasks with a CyclicBarrier
* Without CyclicBarrier: [LionPenManagerChaotic](src/main/java/learn/ocp/progr2/ch07concurrency/LionPenManagerChaotic.java)
* Using CyclicBarrier: [LionPenManagerCyclic](src/main/java/learn/ocp/progr2/ch07concurrency/LionPenManagerCyclic.java)


## Chapter 10 - JDBC

### Setup Derby
* [Setup Derby Database](src/main/java/learn/ocp/progr2/ch10jdbc/SetupDerbyDatabase.java)

### Connecting
* [Getting Database Connection](src/main/java/learn/ocp/progr2/ch10jdbc/GettingDbConnection.java)

### Modifying Data
* [Modifying Data with executeUpdate](src/main/java/learn/ocp/progr2/ch10jdbc/ModifyingDataWithExecuteUpdate.java)

### Reading Data
* [Reading Data with executeQuery](src/main/java/learn/ocp/progr2/ch10jdbc/ReadingDataWithExecuteQuery.java)

### Processing Data with execute
* [ProcessingDataWithExecute](src/main/java/learn/ocp/progr2/ch10jdbc/ProcessingDataWithExecute.java)

### Working with PreparedStatement Parameters
* [WorkingWithParameters](src/main/java/learn/ocp/progr2/ch10jdbc/WorkingWithParameters.java)

### Setup HSQL
* [Setup HSQL Database](src/main/java/learn/ocp/progr2/ch10jdbc/SetupHsqlDatabase.java)

### Calling Procedures
* [Calling Procedure without Parameters](src/main/java/learn/ocp/progr2/ch10jdbc/CallingProcedureWithoutParameters.java)
* [Passing an IN Parameter](src/main/java/learn/ocp/progr2/ch10jdbc/PassingInParameter.java)
* [Returning an OUT Parameter](src/main/java/learn/ocp/progr2/ch10jdbc/ReturningOutParameter.java)
* [Working with INOUT Parameter](src/main/java/learn/ocp/progr2/ch10jdbc/WorkingWithInOutParameter.java)

### Transactions
* [Controlling Transactions](src/main/java/learn/ocp/progr2/ch10jdbc/ControlTransactions.java)

## Chapter 11 - Security

### Customizing the Serialization Process
* [Example of customized Employee class](src/main/java/learn/ocp/progr2/ch11secur/writeread/Employee.java)

### Pre/Post-Serialization Processing applying readResolve() and writeReplace()
* [Example of pre/post processed Employee](src/main/java/learn/ocp/progr2/ch11secur/prepostprocessing/Employee.java)

