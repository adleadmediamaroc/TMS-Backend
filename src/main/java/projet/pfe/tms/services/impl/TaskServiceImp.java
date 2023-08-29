package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.TaskDTO;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.TaskRepo;
import projet.pfe.tms.services.TaskService;
import projet.pfe.tms.services.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService
{

    private final TaskRepo taskRepo;
    private final AgentService agentService;
    private final ClientService clientService;


    @Autowired
    public TaskServiceImp(TaskRepo taskRepo,
                          AgentService agentService,
                          ClientService clientService){
        this.taskRepo = taskRepo;
        this.agentService=agentService;
        this.clientService=clientService;

    }

    @Transactional
    @Override
    public Task addNewTask(TaskDTO taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setTaskDetails(taskDto.getTaskDetails());
        task.setStatus(TaskStatus.PROGRAMMÉE);
        task.setDueDate(taskDto.getDueDate());
        task.setPlanNextAction(taskDto.getPlanNextAction());
        task.setPriority(taskDto.getPriority());
        task.setRepeatEvery(taskDto.getRepeatEvery());
        task.setModule(taskDto.getModule());


        // set task's country
        if(taskDto.getAgentId() != null) {
            Agent taskAgent = this.agentService.loadAgentById(taskDto.getAgentId());
            task.setAgent(taskAgent);
        }
        if(taskDto.getClientId() != null) {
            Client taskClient = this.clientService.loadClientById(taskDto.getClientId());
            task.setClient(taskClient);
        }

        return this.taskRepo.save(task);
    }

    @Transactional
    @Override
    public Task updateTask(Long id, TaskDTO taskDto) {
        Task task = this.taskRepo.findById(id).orElse(null);
        if(task != null){
            task = this.updateData(task, taskDto);

            if(taskDto.getAgentId() != null) {
                Agent taskAgent = this.agentService.loadAgentById(taskDto.getAgentId());
                task.setAgent(taskAgent);
                task.setClient(null);

            }
            else if(taskDto.getClientId()!=null){
            Client taskClient = this.clientService.loadClientById(taskDto.getClientId());
            task.setClient(taskClient);
            task.setAgent(null);

            }



            return this.taskRepo.save(task);
        }
        return null;
    }

    @Override
    public Task updateData(Task task, TaskDTO taskDto) {
        task.setName(taskDto.getName());
        task.setTaskDetails(taskDto.getTaskDetails());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        task.setPlanNextAction(taskDto.getPlanNextAction());
        task.setPriority(taskDto.getPriority());
        task.setRepeatEvery(taskDto.getRepeatEvery());
        task.setModule(taskDto.getModule());

        return task;
    }

    @Override
    public void deleteTask(Long id) {

//        taskRepo.deleteById(id);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));
        if(task.getAgent()!=null) {
            List<Task> agentTasks = task.getAgent().getTasks();
        agentTasks.remove(task);}
        else if (task.getClient()!=null) {
            List<Task> clientTasks = task.getClient().getTasks();
            clientTasks.remove(task);

        }
        taskRepo.delete(task);
    }

    @Override
    public List<Task> listTasks() {
        return this.taskRepo.findAll();
    }

    @Override
    public List<TaskDTO> listTasksDTO() {
        List<Task> tasks = this.taskRepo.findAll();
        List<TaskDTO> tasksDTO = tasks.stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
        return tasksDTO;
    }

    public TaskDTO convertToTaskDTO(Task task) {
         if(task == null){
            return null;
        }
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setTaskDetails(task.getTaskDetails());
        taskDto.setStatus(task.getStatus());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setPlanNextAction(task.getPlanNextAction());
        taskDto.setPriority(task.getPriority());
        taskDto.setRepeatEvery(task.getRepeatEvery());
        taskDto.setModule(task.getModule());

        if(task.getAgent() != null)
            taskDto.setAgentId(task.getAgent().getAgentId());
        if(task.getClient() != null)
            taskDto.setClientId(task.getClient().getClientId());

        return taskDto;
    }

    @Override
    public Task loadTaskById(Long id) {
        return this.taskRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public TaskDTO loadTaskByTaskId(Long id) {
        Task task = this.taskRepo.findById(id).orElse(null);
        if(task == null){
            return null;
        }
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setTaskDetails(task.getTaskDetails());
        taskDto.setStatus(task.getStatus());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setPlanNextAction(task.getPlanNextAction());
        taskDto.setPriority(task.getPriority());
        taskDto.setRepeatEvery(task.getRepeatEvery());
        taskDto.setModule(task.getModule());

        if(task.getAgent() != null)
            taskDto.setAgentId(task.getAgent().getAgentId());
        if(task.getClient() != null)
            taskDto.setClientId(task.getClient().getClientId());

        return taskDto;
    }


    @Override
    public Task loadTaskByTaskDetails(String taskDetails) {
        return this.taskRepo
                .findByTaskDetails(taskDetails)
                .orElse(null);
    }



    @Override
    public Task affectAgentToTask(Long idTask, Long idAgent) {
        Task task = this.loadTaskById(idTask);
        Agent agent = this.agentService.loadAgentById(idAgent);
        if(task != null){
            task.setAgent(agent);
            return this.taskRepo.save(task);
        }
        return null;
    }

    @Override
    public Task affectClientToTask(Long idTask, Long idClient) {
        Task task = this.loadTaskById(idTask);
        Client client = this.clientService.loadClientById(idClient);
        if(task != null){
            task.setClient(client);
            return this.taskRepo.save(task);
        }
        return null;
    }

    @Override
    public int countTotalTasks() {
        return (int) taskRepo.count();
    }

    @Override
    public int countScheduledTasks() {
        return taskRepo.countByStatus(TaskStatus.PROGRAMMÉE);
    }

    @Override
    public int countDoneTasks() {
        return taskRepo.countByStatus(TaskStatus.EFFECTUÉE);
    }
    @Override
    public int countCanceledTasks() {
        return taskRepo.countByStatus(TaskStatus.ANNULÉE);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepo.findByTaskId(taskId);
        task.setStatus(status);
        return taskRepo.save(task);
    }
}
