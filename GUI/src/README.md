# Source Folder - GUI Classes

This folder contains the source code classes needed to create the graphical user interface (GUI) of the project.

## GUI Module and Classes

The classes in this folder collectively define the various components and functionalities of the GUI. These classes work together to create a cohesive user interface for the project.

As the project evolves, additional classes may be added to this folder to extend or enhance the GUI's capabilities.

### Module 'Utilities'
This file (module) contains the classes that are useful for the interaction of this GUI project and custom-made components, each with actions and mouse listeners. It acts as the 'Tool' module for the project.

#### Contains

- **RoundedPanel**: A rounded panel that has curved edges and extends 'JPanel'.

- **HoverableButton**: An abstract class for a Button that is transparent, zooming in when hovered over—hence the name. It has an abstract method 'showEffect' that shows effects for mouse actions and extends the JButton.

- **TransparentButton**: A class for a transparent button that inherits from **HoverableButton** and overrides the 'showEffect' method to change the icon when clicked.

- **ColoredButton**: A class for a round button that inherits from **HoverableButton**. It uses 'RoundedPanel' as a background and overrides the showEffect method to change the background color.

- **ImageIcons**: A static final class that provides a collection of image icons as static fields from images stored in two subfolders under the icon folder: 'color' and 'dark'. It has a static method `reSizeImageIcon` to resize any given image icon to a specified width and height.

- **CustomTableCellRenderer**: A cell renderer that colors a JTable with a palette (mixed style) of two types of colors.

- **MembersTableListener**: This table litsener creates and shows an individual profile page based on the row touched in the MembersList JTable in MembersPanel which is the list of all the members.

- **ParentPanel**: An interface is created for the generalization of showEffect method in the TransparentButton

- **queryPanel**: A custom panel is to be used in the adding members and showing individual members. It has two versions for recieving and showing information.

### Classes 
Further descriptions about the classes can be found in their respective class comments.
