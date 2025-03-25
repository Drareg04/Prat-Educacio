document.addEventListener('DOMContentLoaded', function() {
  const addTaskBtn = document.getElementById('addTaskBtn');
  const taskInput = document.getElementById('taskInput');
  const taskList = document.getElementById('taskList');

  function addTask() {
      const taskText = taskInput.value.trim();
      if (taskText !== '') {
          const newTask = document.createElement('li');
          newTask.textContent = taskText;

          const deleteBtn = document.createElement('button');
          deleteBtn.textContent = 'Eliminar';
          deleteBtn.style.backgroundColor = 'red';  
          deleteBtn.style.color = 'white';          
          deleteBtn.style.border = 'none';          
          deleteBtn.style.padding = '5px 10px';     
          deleteBtn.style.marginLeft = '10px';      
          deleteBtn.style.cursor = 'pointer';   
          deleteBtn.onclick = () => newTask.remove();

          newTask.appendChild(deleteBtn);
          taskList.appendChild(newTask);

          taskInput.value = '';
      }

      if (taskText === '') {
          alert('This can not be void');
      }
  }

  addTaskBtn.addEventListener('click', addTask);
  taskInput.addEventListener('keypress', function (e) {
      if (e.key === 'Enter') {
          addTask();
      }
  });
});