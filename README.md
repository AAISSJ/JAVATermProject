# JAVATermProject
- SWE2023 : JAVA Programming Lab (Spring, 2021)
- 2021.06.06 GUI to select furnitures and decorate a virtual room for a change

![image](https://user-images.githubusercontent.com/76966915/222941535-a6e6cbce-d7be-4a7e-8cb9-3442842ffdb2.png)


## Description 

![image](https://user-images.githubusercontent.com/76966915/222941548-92fb2241-4b11-4b22-ae89-3926d7c5d643.png)

In first screen, you can enter user name and your budget to buy furnitures. 
If you don't enter any of them, the system returns message to ask fill the blank.

![image](https://user-images.githubusercontent.com/76966915/222941557-118524e2-b7b3-4b0c-b1db-24f9d96a6a1d.png)

On the top, you can see a list of furnitures. If you want to buy some of them, you can choose it and add to your own favorite list. But if the sum of cost exceeds your budget, program returns the warning message to notice you to overspend budget.

From your Favorites list, you can import the images of funitures you want. 
Also, images are draggable.  So you can arrange the images on the screen to simulate your room. After arrange, click a button to save the screenshot. 

## Functionalities

This gui has three main functions. First, it can check and store the furnitures that you want. So I use Jtables and Jbuttons to implement this function. After make a table which store total information of furnitures, I implement two buttons, to add and to delete. For buttonAdd, take a data of a row which i selected and put them in to second table. Also sum the price of furnitures and show it in the label which is in the button of frame. In the contrast for buttonDelete, delete a data of a row which i selected and substract the price of furniture from the current cost. 
