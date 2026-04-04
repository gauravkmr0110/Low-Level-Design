/*
Design an in-memory file system similar to a basic version of Google Drive or Dropbox where:

    - Users can create files and directories
    - Directories can contain files or other directories
    - Support common operations like: Create, Delete, Move, Search, List contents
🔹 Functional Requirements
Create a file
Create a directory
Delete file/directory
Move file/directory
List contents of a directory
Search files/directories by name
Get full path of a file
🔹 Non-Functional Requirements
Should be scalable (handle large hierarchy)
Efficient operations (O(log n) or better where possible)
Extensible (permissions, metadata later)
🔹 Constraints / Assumptions
In-memory system (no DB required initially)
Each file/directory has:
Name
Created timestamp
Size (for file)
Directory can contain multiple children
No duplicate names in same directory

*/
