import os
import requests
import openai

class CardGenerator:
    def __init__(self, api_key):
        openai.api_key = api_key
        
    def generate_card(self, prompt):
        response = openai.Image.create(
            prompt=prompt,
            n=1,
            size="1024x1024"
        )
        
        # Extracting the URL from the API response
        image_url = response['data'][0]['url']
        return image_url
    
    def download_and_save_image(self, url, folder, filename):
        response = requests.get(url)
        
        if response.status_code == 200:
            filepath = os.path.join(folder, filename)
            with open(filepath, 'wb') as file:
                file.write(response.content)
            return filepath
        else:
            print(f"Failed to download image from {url}")
            return None
    
    def generate_and_save_cards_from_file(self, filename, folder):
        with open(filename, 'r', encoding='utf-8') as file:
            for line in file:
                # Assuming each line in the file is a character description
                # And character name is included in each description line
                character_description = line.strip()
                
                # Extract character name from the description
                # Adjust this part based on how the character name is included in the description
                character_name = character_description.split("\n")[0].split(": ")[1]
                
                # Construct a prompt for DALL·E
                prompt = f"Create an illustrated pixel character card for the following description also dont do any text on the card:\n{character_description}"
                
                # Generate card using DALL·E API
                card_url = self.generate_card(prompt)
                
                # Download and save the image to the specified folder with character name as filename
                if card_url:
                    filename = f"{character_name}.png"
                    filepath = self.download_and_save_image(card_url, folder, filename)
                    
                    if filepath:
                        print(f"Card for {character_name} saved at {filepath}")
                    else:
                        print(f"Failed to save card for {character_name}")
                else:
                    print(f"Failed to generate card for {character_name}")


api_key = '%API_Key%'  # Replace with your OpenAI API Key
folder = 'pic'  # Replace with your folder path
filename = 'cyberpunk_characters.txt'


generator = CardGenerator(api_key)
generator.generate_and_save_cards_from_file(filename, folder)