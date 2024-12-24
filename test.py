from pydub import AudioSegment

audio = AudioSegment.from_mp3("input.mp3")
audio.export("output.wav", format="wav")
