package mgneraapp;

import classes.BDODAO;
import classes.ProjectDAO;
import classes.UserDAO;
import exceptions.LoginException;
import exceptions.SignUpException;
import models.ProjectModel;
import models.UserModel;
import java.sql.SQLException;
import exceptions.ProjectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BDOMenu {
    private final UserModel um = new UserModel();
    private final Scanner sc = new Scanner(System.in);
    private final UserDAO ud = new UserDAO();
    private final ProjectDAO pd = new ProjectDAO(); 
    private final BDODAO bd =new BDODAO();
    private final SignUpException se = new SignUpException("");
    private final LoginException le = new LoginException("");
    private static final String STATUS_PENDING = "Pending";
    private static final String STATUS_IN_PROGRESS = "In Progress";
    private static final String STATUS_COMPLETED = "Completed";
    private String loggedInUserRole;
    BDODAO bdo1=new BDODAO();

    public void signup() {
        boolean isValidUsername = false;

        while (!isValidUsername) {
            System.out.println("Enter the username:");
            String username = sc.nextLine();
            if (se.checkAvailUsername(username)) {
                um.setUsername(username);
                isValidUsername = true;
            } else {
                System.out.println("Username " + username + " is already assigned. Please try with a new username.");
            }
        }

        System.out.println("Enter the password:");
        um.setPassword(sc.nextLine());

        System.out.println("Enter the phone number:");
        um.setPhoneNo(sc.nextLine());

        System.out.println("Enter the email id:");
        um.setEmail(sc.nextLine());

        System.out.println("Enter the address:");
        um.setAddress(sc.nextLine());

        um.setRole("BDO");

        try {
            ud.signUp(um);
        } catch (SignUpException se) {
            System.out.println(se.getMessage());
        }
    }

    public void login() throws LoginException {
    System.out.println("Enter the username:");
    String username = sc.nextLine();

    System.out.println("Enter the password:");
    String password = sc.nextLine();

    UserModel user = ud.login(username, password);
//    loggedInUserRole = user.getRole();  // Store the role of the logged-in user
//    System.out.println("Login successful as " + loggedInUserRole);
}
//    public void showMenu() throws ProjectException {
//        while (true) {
//            System.out.println("1. Create Project");
//            System.out.println("2. View Project");
//            System.out.println("3. Create New GPM");
//            System.out.println("4. View All GPM");
//            System.out.println("5. Allocate Project to GPM");
//            System.out.println("6. See Employees");
//            System.out.println("7. Exit");
//
//            int choice = sc.nextInt();
//            sc.nextLine();  // Consume newline
//
//            switch (choice) {
//                case 1:
//                    createProject();
//                    break;
//                case 2:
//                    viewProject();
//                    break;
//                case 3:
//                    createNewGPM();
//                    break;
//                case 4:
//                    viewAllGPMs();
//                    break;
//                case 5:
//                    allocateProjectToGPM();
//                    break;
//                case 6:
//                    seeEmployees();
//                    break;
//                case 7:
//                    System.out.println("Exiting...");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }

    public void createProject() {
        ProjectModel project = new ProjectModel();
        
        try {
            System.out.println("Enter project name: ");
            project.setProjectName(sc.nextLine());

            System.out.println("Enter description: ");
            project.setDescription(sc.nextLine());
            
             String status = "";
             boolean validStatus = false;
             while (!validStatus) {
            System.out.println("Enter the project status (Pending, In Progress, Completed):");
            status = sc.nextLine();

            if (status.equals(STATUS_PENDING) || status.equals(STATUS_IN_PROGRESS) || status.equals(STATUS_COMPLETED)) {
                validStatus = true;
            } else {
                System.out.println("Invalid status. Please choose from (Pending, In Progress, Completed).");
            }
        }
            pd.createProject(project);
            System.out.println("Project created successfully.");

        } catch (ProjectException e) {
            System.out.println("Project creation error: " + e.getMessage());
        }
    }

public void viewProject() {
    System.out.println("Enter project ID to view: ");
    int projectId = sc.nextInt();
    sc.nextLine();  // Consume the newline

    try {
        ProjectModel project = pd.getProjectById(projectId);
        if (project != null) {
            System.out.println(project);
        } else {
            System.out.println("Project not found.");
        }
    } catch (ProjectException e) {
        System.out.println(e.getMessage());
    }
}

public void createNewGPM() {
    UserModel gpm = new UserModel();

    boolean isValidUsername = false;

    while (!isValidUsername) {
        System.out.println("Enter the username:");
        String username = sc.nextLine();
        if (se.checkAvailUsername(username)) {
            gpm.setUsername(username);  // Set the username on the gpm object
            isValidUsername = true;
        } else {
            System.out.println("Username " + username + " is already assigned. Please try with a new username.");
        }
    }

    System.out.println("Enter the password:");
    gpm.setPassword(sc.nextLine());  
    
    System.out.println("Enter the phone Number:");
    gpm.setPhoneNo(sc.nextLine());  
    
    System.out.println("Enter the email id:");
    gpm.setEmail(sc.nextLine());  

    System.out.println("Enter the address:");
    gpm.setAddress(sc.nextLine());  

    gpm.setRole("GPM");  

    try {
        ud.signUp(gpm);  // Use the gpm object for signing up
        System.out.println("GPM created successfully!");
    } catch (SignUpException e) {
        System.out.println("Error creating GPM: " + e.getMessage());
    }
}



   public void viewAllGPMs() {
    try {
        List<UserModel> gpmList = bdo1.viewAllGPMs();
        if (gpmList.isEmpty()) {
            System.out.println("No GPMs found.");
        } else {
            for (UserModel gpm : gpmList) {
                System.out.println(gpm);  // Ensure your UserModel has a suitable toString() method
            }
        }
    } catch (LoginException e) {
        System.out.println("Error fetching GPMs: " + e.getMessage());
    }
}

public void viewAllProjects() {
    try {
        List<ProjectModel> projects = pd.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
        } else {
            System.out.println("Available Projects:");
            for (ProjectModel project : projects) {
                System.out.println("ID: " + project.getProjectId() +
                                   ", Name: " + project.getProjectName() +
                                   ", Status: " + project.getStatus());
            }
        }
    } catch (ProjectException e) {
        System.out.println("Error retrieving projects: " + e.getMessage());
    }
}


  public void allocateProjectToGPM() throws ProjectException {
    // View all available projects
    viewAllProjects();
    
    // View all available GPMs
    viewAllGPMs();

    // Get input from the user
    System.out.println("Enter the project ID to allocate: ");
    int projectId = sc.nextInt();
    sc.nextLine();  // Consume the newline character

    System.out.println("Enter the GPM username to allocate the project to: ");
    String gpmUsername = sc.nextLine();

    try {
        // Call the DAO method to allocate the project
        bdo1.allocateProjectToGPM(projectId, gpmUsername);
    } catch (ProjectException | LoginException e) {
        System.out.println("Error: " + e.getMessage());
    }
}



   public void seeEmployees() {
    try {
        // Fetch the list of employees (assuming a method exists in UserDAO)
        List<UserModel> employees = bdo1.getAllEmployees();  // You need to create this method
        
        // Check if there are employees to display
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("Employees List:");
            for (UserModel employee : employees) {
                System.out.println("Username: " + employee.getUsername() +
                                   "\nPhone Number: " + employee.getPhoneNo() +
                                   "\nEmail: " + employee.getEmail() +
                                   "\nAddress: " + employee.getAddress() +
                                   "\nRole: " + employee.getRole() + "\n");
            }
        }
    } catch (Exception e) {
        System.out.println("Error retrieving employees: " + e.getMessage());
    }
}

    public void searchGPM() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the GPM username to search:");
    String username = sc.nextLine();

    try {
        // Assuming ud is an instance of UserDAO or equivalent
        UserModel gpm = bd.searchGPM(username);

        if (gpm != null) {
            System.out.println("GPM Details:");
            System.out.println("Username: " + gpm.getUsername());
            System.out.println("Phone Number: " + gpm.getPhoneNo());
            System.out.println("Email: " + gpm.getEmail());
            System.out.println("Address: " + gpm.getAddress());
            System.out.println("Role: " + gpm.getRole());
        }

    } catch (SQLException | LoginException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


   public void updateGPM() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the GPM username to update:");
    String username = sc.nextLine();

    try {
        UserModel gpm = bd.searchGPM(username); // Fetch the GPM details

        if (gpm == null) {
            System.out.println("GPM with username " + username + " does not exist.");
            return;
        }

        // Display current details and prompt for changes
        System.out.println("Current Phone Number: " + gpm.getPhoneNo());
        System.out.println("Enter new Phone Number (leave blank to keep unchanged):");
        String phoneNo = sc.nextLine();
        if (!phoneNo.isEmpty()) {
            gpm.setPhoneNo(phoneNo);
        }

        System.out.println("Current Email: " + gpm.getEmail());
        System.out.println("Enter new Email (leave blank to keep unchanged):");
        String email = sc.nextLine();
        if (!email.isEmpty()) {
            gpm.setEmail(email);
        }

        System.out.println("Current Address: " + gpm.getAddress());
        System.out.println("Enter new Address (leave blank to keep unchanged):");
        String address = sc.nextLine();
        if (!address.isEmpty()) {
            gpm.setAddress(address);
        }

        // Update the GPM details in the database
        bd.updateGPM(gpm);
        
    } catch (SQLException | LoginException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


 public void deleteGPM() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the GPM username to delete:");
    String username = sc.nextLine();

    try {
        // Assuming gpmDao is an instance of GPMDAO or equivalent
        bd.deleteGPM(username);

    } catch (SQLException | LoginException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    void searchProjectById() {
         System.out.println("Enter project ID to view: ");
    int projectId = sc.nextInt();
    sc.nextLine();  // Consume the newline

    try {
        ProjectModel project = pd.getProjectById(projectId);
        if (project != null) {
            System.out.println(project);
        } else {
            System.out.println("Project not found.");
        }
    } catch (ProjectException e) {
        System.out.println(e.getMessage());
    }
    }

   public void updateProject() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the project ID to update:");
    int projectId = sc.nextInt();
    sc.nextLine();  // Consume newline

    System.out.println("Enter the new project name:");
    String newName = sc.nextLine();

    System.out.println("Enter the new project description:");
    String newDescription = sc.nextLine();

    System.out.println("Enter the new project status:");
    String newStatus = sc.nextLine();

    try {
        // Assuming pd is an instance of ProjectDAO or equivalent
        pd.updateProject(projectId, newName, newDescription, newStatus);
    } catch (ProjectException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


   public void deleteProject() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the project ID to delete:");
    int projectId = sc.nextInt();

    try {
        pd.deleteProject(projectId);

    } catch (ProjectException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


  
}
