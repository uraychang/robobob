import spacy
import sys

# Load the spaCy model (large model for better accuracy)
nlp = spacy.load("en_core_web_md")

def calculate_similarity(sentence1, sentence2):
    # Process the input sentences
    doc1 = nlp(sentence1)
    doc2 = nlp(sentence2)

    # Calculate similarity
    similarity = doc1.similarity(doc2)
    return similarity

if __name__ == "__main__":

    # Get the sentences from the command line arguments
    sentence1 = sys.argv[1]
    sentence2 = sys.argv[2]

    # Calculate and print the similarity
    similarity = calculate_similarity(sentence1, sentence2)
    print(f"{similarity}")