Lors de la conception d'un logiciel, il est important de séparer les préoccupations de l'application en différentes couches. Dans ce cas, nous pouvons diviser l'application en deux couches principales : la couche domaine et la couche infrastructure.

La couche de domaine est le cœur de l'application et contient toute la logique et les règles de l'entreprise. Elle doit être indépendante de toute infrastructure ou technologie et ne doit contenir que le code directement lié au problème à résoudre. Cela signifie que la couche de domaine doit contenir la définition de l'entité de la tâche, ses propriétés et ses comportements.

La couche infrastructure contient tout le code qui n'est pas directement lié au problème à résoudre mais qui est nécessaire pour exécuter l'application. Il s'agit notamment du code permettant d'accéder au système de fichiers, de gérer l'interface de ligne de commande et de faire persister les données de la tâche sur le disque. La couche d'infrastructure doit être responsable de la traduction de l'entité de tâche dans un format qui peut être stocké dans un fichier, et de la lecture des données du fichier et de leur retour en tant qu'entité de tâche.

Voici une approche possible pour diviser le code en couches de domaine et d'infrastructure :

Couche du domaine :

- Les Models comme celle d’une tâche avec des propriétés telles que Created, DueDate, CloseDate, State, SubTasks.
- Classe TaskService avec des méthodes pour ajouter, supprimer, mettre à jour et lister les tâches, qui mettent en œuvre la logique commerciale de l'application.

Couche d'infrastructure :

- Interface TaskRepository qui définit le contrat de stockage et de récupération des données de tâches.
- Classe TaskFileRepository qui implémente l'interface TaskRepository et qui est responsable du stockage et de la récupération des données de tâches dans un fichier.
- Classe TaskManager qui reçoit les entrées de la ligne de commande, appelle les méthodes appropriées dans le TaskService et gère les E/S avec l'utilisateur. La classe TaskManager agit comme point d'entrée de l'application et coordonne l'interaction entre les différentes couches. La classe TaskManager instancie un objet Logger et un objet TaskService. Elle analyse également les arguments de la ligne de commande et appelle les méthodes appropriées dans le TaskService.
- Les Parseurs d’arguments et de Fichiers, qui permettent de pouvoir récupérer les données JSON et les transforme en objet traitable.
- L’entitée Task qui nous sert de représentation de la donnée récupéré.

Cette approche permet d'améliorer la modularité, la maintenabilité et la testabilité, car les modifications apportées à la couche d'infrastructure n'affectent pas la couche de domaine, et vice versa. En outre, il est plus facile de modifier l'implémentation de la couche d'infrastructure à l'avenir, sans affecter la logique métier de l'application.