# PCBM - PC Build Manager

PCBM is a web application that allows users to browse available PC parts, configure simple PC builds, and manage component inventory.
This project consists of a backend service for data storage and a frontend interface for users to interact with the system.

---

## Features

### User Functionality:
1. **View Components**:
   - Browse available PC components (e.g., CPUs, GPUs, RAM).
2. **Create and Save Builds**:
   - Assemble and save PC builds by selecting from available components.
3. **Export Builds**:
   - Download a summary of PC builds in PDF format.

### Admin Functionality:
1. **Manage Inventory**:
   - Add, update, or delete PC components.

---

## Backend Overview

### Database Structure:
- **Tables**:
  - `Components`: Stores details like ID, name, type, and cost.
  - `Builds`: Records PC builds created by users with selected components.
  - `Users`: Manages user accounts, authentication, and roles.
  - `Categories`: Organizes components into categories such as "CPU" and "GPU".

- **Relationships**:
  - Each build references multiple components and an owner (user).
  - Components are assigned a category.

### API Endpoints:
1. **Components**:
   - `GET`, `POST`, `PUT`, `DELETE` endpoints for managing components.
2. **Builds**:
   - `GET`, `POST`, `PUT`, `DELETE` endpoints for managing builds.
   - Pagination and filtering for listing components.
3. **Export PDF**:
   - Export a PC build summary as a downloadable PDF.

---

## Frontend Overview

### Views:
1. **Home**: Displays all PC builds for the logged-in user.
2. **Build Creator**: Allows users to create a PC build.
3. **Build Summary**: Displays details of a saved build and provides an export option.
4. **Admin Panel**: Lists all components (admin only).
5. **Component Creator**: Enables admins to create or update components.
6. **Profile Info**: Displays user information and provides a logout option.

### Navigation Flow:
1. **Login Page**:
   - Redirects to the Home page upon successful login.
2. **Home Page**:
   - Users can navigate to the Build Creator, Build Summary, Profile Info, or Admin Panel (if admin).
3. **Build Creator**:
   - Allows saving builds and navigating to other views.
4. **Build Summary**:
   - Provides options to update or export the build.
5. **Admin Panel**:
   - Allows admins to add or edit components.
6. **Profile Info**:
   - Users can log out or navigate to other views.

---

## Additional Documentation

1. **Swagger/OpenAPI**:
   - Access API documentation at `/swagger-ui.html` after running the backend.
2. **UML Diagram**:
   - Refer to the UML diagram in the `docs/diagram.png` file for the database model.
3. **Postman Collection**:
   - Use the provided Postman collection (`docs/PCBM - PC Build Manager.postman_collection.json`) for testing the API endpoints.


