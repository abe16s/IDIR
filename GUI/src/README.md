# Source Folder - GUI Classes

This folder contains the source code classes needed to create the graphical user interface (GUI) of the project.

## GUI Classes

The classes in this folder collectively define the various components and functionality of the GUI. These classes work together to create a cohesive user interface for the project.

As the project evolves, additional classes may be added to this folder to extend or enhance the GUI's capabilities.

### 'ImageIcons'
This class is a utility class that provides a collection of image icons for the graphical user interface (GUI) of the project. 
The image icons are stored in two subfolders under the icon folder: 'colored' and 'dark'.
The colored folder contains the same images as the dark folder, but colored. These images are used when the buttons are clicked.
The dark folder contains the images used for the buttons in their default state. These images are black and white.

#### Class Features
- **Static Method: `reSizeImageIcon`**: Resizes any given image icon to a specified width and height. 
- The class is final, which means it cannot be inherited by other classes. The class is located under the 'src' folder of the project.
- To use the image icons in other classes, you need to import this class and access the image icons by their names, such as 'ImageIcons.OFFICIAL' or 'ImageIcons.COINS'. 
- You can also use the resize method to adjust the size of the image icons according to your needs.
