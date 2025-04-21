
# Task Tracker CLI

Questo è un semplice progetto CLI per monitorare le tue attività. Puoi aggiungere, aggiornare, eliminare e visualizzare le attività.

## Requisiti

- Java 11 o superiore
- Maven

## Installazione

1. Clona il repository:

   git clone https://github.com/Vito-28/TaskTrackerCLI.git
   
   cd task-tracker-cli

2. Compila il progetto:

   mvn clean install

3. Esegui l'applicazione:

   mvn exec:java

## Comandi

- **Aggiungi attività**:

  task-cli add "Descrizione dell'attività"
  
- **Aggiorna o Elimina le attività**:

  task-cli update 1 "Test Update"
  
  task-cli delete 1

- **Visualizza tutte le attività**:

  task-cli list
  
- **Visualizza le attività in base allo stato**:

  task-cli list done
  
  task-cli list todo
  
  task-cli list in-progress

- **Contrassegna attività come in-progress, done, todo**:

  task-cli mark-in-progress 1
  
  task-cli mark-done 1
  
  task-cli mark-todo 1

## Come funziona

Ogni attività ha una descrizione e uno stato (da fare, in corso, completata). Le attività vengono salvate in un file JSON.

## Licenza

Questo progetto è distribuito sotto la Licenza MIT.
