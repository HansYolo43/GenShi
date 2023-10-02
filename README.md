# GenShi

Problem Domain:

The problem domain our group would like to focus on is simple gaming. Some basic knowledge of gaming would be required to understand the project. 


Our Application:

Our team would like to develop a game which is based on luck mostly. It is a one player game against the computer where the characters are assigned randomly. Each character has certain statistics which make them stronger or weaker than the computers'. The more games you win against the computer, the more of the character cards you get in your bounty. However, this depends entirely on the stats of your randomly assigned card. The aim of this game is for the user to get all the cards in the system, possibly before the computer does.

We aim to use two API's to make this possible:

1. OPENAI API (GPT and DALL - E)
    https://platform.openai.com/docs/introduction/overview

   We believe GPT will be useful for the random generation of stats and general randomness of our game. DALL- E will be useful for generating images of our randomised playable characters.



2. Google
   https://developers.google.com/explorer-help

   We wanted to embed this in the user sign up/login part of our game. However this is not an api that requires focus for our problem domain and general idea. We just thought to add it as part of our plan in the future. Our project relies more on (1)



Here are some screenshots of us trying out some OpenAi requests on https://hoppscotch.io/:
<img width="1079" alt="Screenshot 2023-10-01 at 9 22 37 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/ef69e17b-be8d-449c-bfed-764e7a260f57">

<img width="1085" alt="Screenshot 2023-10-01 at 9 20 48 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/7588f29f-aba9-4a74-b346-26ccbf70f292">




Here is the main java code that generates the stats we need for our randomised characters using GPT:

<img width="986" alt="Screenshot 2023-10-01 at 8 30 22 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/f4559247-e996-40e0-a643-fe4d3bbcb15a"> 

However, it is not very obvious that it is using the api, so here is the body of one of the helper methods used within called "generate name" (along with some other details above that we thought were useful to belabour showing the use of the api)

<img width="899" alt="Screenshot 2023-10-01 at 8 27 38 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/d217132a-cdc8-4080-abd9-a8b218fa4fd8">  


The result of this call is a huge txt files of different characters,descriptions and stats as seen below:
<img width="1308" alt="Screenshot 2023-10-01 at 8 20 47 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/db61ab0d-7f7a-4b94-94ce-2cc8ee4fa092">  


These are alot, so an example of one of the characters in the txt file is shown below for clarity:
<img width="625" alt="Screenshot 2023-10-01 at 8 22 58 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/9c7c2580-9ed9-4625-8618-2b96477c4f08">



As for our image generation from openai, here is some code written to get the character image player cards using dall-e.

<img width="735" alt="Screenshot 2023-10-01 at 9 34 04 PM" src="https://github.com/HansYolo43/GenShi/assets/112582923/ca5e4b35-50ba-4b1a-9864-37ebf1e3a932">

However, another constraint that we noticed was that dall-e was not very good at reading prompts. It gave low quality ai images such as the one below:

!['Neon Moxxi', 'Attack Name'](https://github.com/HansYolo43/GenShi/assets/112582923/c1fbbd09-bf9a-47f3-935d-1d49c364d974)



However, to show our idea better, we used a local image generation ai instead. We will document this better in our techincal contraints part.

Our ideal result of the image api we would like to use would be something like this:


![Penelope the Wanderlust](https://github.com/HansYolo43/GenShi/assets/112582923/69b742d5-65e2-4a4a-afe0-078ba48e6b63) 

(gotten from SDXL. https://stablediffusionxl.com/)





We noticed something that may be a technical constraint:
1. We needed to generate several images. However, many image apis would restrict us to the number of calls we could make. If we wanted to make more calls, we had to pay a fee.
   We decided to go with DALL-E for now to visualise our idea. However, help in this area would be greatly appreciated.
2. We needed a smarter AI Api to really make our idea shine through. Again, help would be appreciated here. SDXL was very receptive to the openai prompts.

   
   
   



