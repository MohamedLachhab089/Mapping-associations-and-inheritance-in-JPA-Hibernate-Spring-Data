# Projet de Mapping des Associations et de l'Héritage en JPA Hibernate avec Spring Data

Ce projet illustre le mapping des associations et de l'héritage en utilisant JPA Hibernate avec Spring Data.

## Classes d'Entités

Nous avons deux classes d'entités principales :

### Classe User
```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    private String userId;

    @Column(name = "USER_NAME", unique = true, length = 20)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
```

### Classe Role
```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class Role {
    @Id
    private String roleId;

    @Column(name = "Description")
    private String desc;

    @Column(name = "ROLE_NAME", unique = true, length = 20)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users = new ArrayList<>();
}
```

## Repositories

Nous utilisons des interfaces qui étendent `JpaRepository` pour effectuer les opérations CRUD sur les entités :

- `UserRepository`
- `RoleRepository`

## Service

Nous avons une interface `UserService` et son implémentation `UserServiceImpl` pour gérer les opérations liées aux utilisateurs et aux rôles.

## Contrôleur

Le contrôleur `UserController` expose un point de terminaison REST pour récupérer les détails d'un utilisateur par son nom d'utilisateur.

## Exemple d'Utilisation

Dans la classe principale `JpaApplication`, nous utilisons un `CommandLineRunner` pour effectuer des opérations de test à chaque démarrage de l'application. Nous ajoutons des utilisateurs, des rôles et associons des rôles aux utilisateurs. Ensuite, nous essayons d'authentifier un utilisateur.

## Utilisation

Pour utiliser ce projet :

1. Assurez-vous d'avoir une configuration de base de données valide dans votre fichier `application.properties`.
2. Exécutez la classe principale `JpaApplication`.
