import openai
import random

class CharacterGenerator:
    def __init__(self, api_key, theme_prompt):
        openai.api_key = api_key
        self.theme_prompt = theme_prompt
        
    def generate_name(self, prompt):
        response = openai.Completion.create(
            engine="text-davinci-003",
            prompt=prompt,
            temperature=0.7,
            max_tokens=50
        )
        return response.choices[0].text.strip()
        
    def generate_description(self, character_name, attack_name, defense_name, defense_stat, attack_stat_1, attack_stat_2):
        response = openai.Completion.create(
            engine="text-davinci-003",
            prompt=f"Describe a character named {character_name} with {defense_name} defense stat {defense_stat} and attack names {attack_name} with stats {attack_stat_1} or {attack_stat_2} in the {self.theme_prompt} theme, Also keep in Mind so Dall-E can draw a good image with it:",
            temperature=0.7,
            max_tokens=100
        )
        return response.choices[0].text.strip()
        
    def generate_character(self):
        character_name = self.generate_name(f"Generate a unique {self.theme_prompt} themed character name:")
        attack_name = self.generate_name(f"Generate a unique {self.theme_prompt} themed attack name:")
        defense_name = self.generate_name(f"Generate a unique {self.theme_prompt} themed defense name:")
        
        defense_stat = random.randint(1, 100)
        attack_stat_1 = random.randint(1, 100)
        attack_stat_2 = random.randint(1, 100)
        
        character_description = self.generate_description(character_name, attack_name, defense_name, defense_stat, attack_stat_1, attack_stat_2)
        print(character_description)

        return {
            "Character Name": character_name,
            "Attack Name": attack_name,
            "Defense Name": defense_name,
            "Defense Stat": defense_stat,
            "Attack Stat Options": [attack_stat_1, attack_stat_2],
            "Character Description": character_description
        }
    def generate_and_save_characters(self, num_characters, filename):
        with open(filename, 'w', encoding='utf-8') as file:  # Specify utf-8 encoding here
            for _ in range(num_characters):
                character = self.generate_character()
                file.write(str(character) + '\n')


class CyberpunkCharacterGenerator(CharacterGenerator):
    def __init__(self, api_key):
        super().__init__(api_key, "odyssey")

api_key = '%API_KEY%'
generator = CyberpunkCharacterGenerator(api_key)

# Generate and save 50 characters to a text file.
generator.generate_and_save_characters(50, 'odyssey_characters.txt')

