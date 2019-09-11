# Module Manager
This is the Module Manager I created for my new Minecraft hacked client.<br>
You can use it on your hacked client, just leave me credits.<br>
The module manager has been tested with the following Minecraft versions:
- 1.8.8
 
 # Terms Of Service
 You can use the module manager in your code for free, but you must leave me credits. You can create the modules under the package me.seba4316.utilities.module to use the protected objects as protected instead of private (more infos in the class me.seba4316.utilities.module.Module), or you can import the classes in your package, but you must leave credits somewhere.
 
 # Setup
 ## Step 1 - Copying The Files
 Copy the folder "me" (with all of the content) from the zip you downloaded into the sources of your project
 ## Step 2 - Initializing in your hacked client main class
 In the main class of your hacked client create the CommandManager instance with<br>
 > private ModuleManager moduleManager;<br>
 
 In the init function, put this code, where trigger is the first char that makes the commands recognizable:
 > this.moduleManager = new ModuleManager();<br>
 
 And last thing: create the return method, always in the main class:
 > public ModuleManager getModuleManager() { // Make it static if you don't use instances for your client<br>
 >		return moduleManager;<br>
 > }
 ## Step 3 - Hooking Into Minecraft
 ### 1. Hooking the key pressed
 **First Method (Step 2 required):**
 
 Clone my GitHub repository: https://github.com/seba4316/Events
 
 Copy the files inside the zip into your sources folder
 
 Go to the void #onTick() in the class net.minecraft.client.Minecraft, search for "if (k == 1)", and put this code right above it:
 > EventKey eventKey = new EventKey(k);<br>
 > eventKey.call();
 
 **Second Method:**
 
 Go to the void #onTick() in the class net.minecraft.client.Minecraft, search for "if (k == 1)", and put this code right above it:
 > YourHackedClient.getInstance().getModuleManager().toggle(k); // If you don't use instances you can remove the #getInstance()
 ### 2. Hooking the events (optional, required if you used the first method in the step 1)
 To hook the events (the ones in the event manager), you can follow this tutorial: https://www.youtube.com/watch?v=OOrVK6s1wrM
 
 
 # Buy me a coffee
You can <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=seba14%40outlook.it&item_name=Buy+me+a+coffee&currency_code=EUR&source=url">donate me</a>, so that I can bring more stuff to github.
