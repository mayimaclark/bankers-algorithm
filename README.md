# bankers-algorithm
This is a quick implementation of Banker's algorithm using Java. The project can be imported into Eclipse.

Detailed explanation: in an operating system, deadlock occurs when there is indefinite waiting for processes that are competing for system resources. For it to occur, foud conditions must be met simultaneously. First, there needs to be mutual exclusion where only one process can access a resource at a time and the process has exclusive use of it. Next, there needs to be hold and wait, wich means a process acquires and holds one or more resources and requests additional resources. Then, circular wait must occur where a closed path of resource allocations and requests is present. Finally, there must be no preemption where a process's execution cannot be interrupted and it cannot be forced to release recourses it's acquired and held.

To handle deadlock, we usually use prevention, avoidance, and detection and recovery. The first two guarantee deadlock will not occur by disallowing at least one of the four conditions to happen. The third method allows the system to reach deadlock, however, and recovers it.

For Banker's algorithm, the operating system must known the max amount of resources every process needs. The algorithm prevents deadlock by granting or denying allocation of resources to processes requesting them.

To determine whether to allow a resource to be allocated to a process, two assumptions must be made: all processes are present before execution has begun and all max claims are given by processes before execution has begun. Once these assumptions are met, the system is checked. If safe, execution continues. If unsafe, the process claiming more resources than available cannot begin execution. Once execution begins, a process can request a resource. The OS pretends to allocate the requested resources to the process and once acquired, the system is checked if the resulting state is safe. If safe, the request is granted. If unsafe, the request is denied and the process is blocked or terminated. When a resource is deallocated from a process by the OS, the next process requesting resources is considered for allocation.

The algorithm continues in this way, pretending to allocate resources, ensuring the state of the system remains safe, and making sure all processes can execute to completion.
