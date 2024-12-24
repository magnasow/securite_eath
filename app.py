from pydub import AudioSegment
from io import BytesIO
import speech_recognition as sr
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/speech-to-text', methods=['POST'])
def speech_to_text():
    try:
        if 'file' not in request.files:
            return jsonify({"error": "No file part"}), 400

        file = request.files['file']

        if file.filename == '':
            return jsonify({"error": "No selected file"}), 400

        # Convert audio file to WAV if necessary
        audio = AudioSegment.from_file(file)
        wav_io = BytesIO()
        audio.export(wav_io, format="wav")
        wav_io.seek(0)

        recognizer = sr.Recognizer()
        audio_file = sr.AudioFile(wav_io)

        with audio_file as source:
            audio_data = recognizer.record(source)

        texte_converti = recognizer.recognize_google(audio_data, language="fr-FR")
        
        return jsonify({"text": texte_converti})

    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
