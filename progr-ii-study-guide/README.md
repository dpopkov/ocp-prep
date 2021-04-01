# Examples from "OCP Java SE 11 Programmer II Study Guide"

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
