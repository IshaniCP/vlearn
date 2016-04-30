interface SpeechSynthesis : EventTarget {
        readonly attribute boolean pending;
        readonly attribute boolean speaking;
        readonly attribute boolean paused;

        attribute EventHandler onvoiceschanged;

        void speak(SpeechSynthesisUtterance utterance);
        void cancel();
        void pause();
        void resume();
        sequence<SpeechSynthesisVoiceList> getVoices();
    };

    [NoInterfaceObject]
    interface SpeechSynthesisGetter
    {
        readonly attribute SpeechSynthesis speechSynthesis;
    };

    Window implements SpeechSynthesisGetter;

    [Constructor,
     Constructor(DOMString text)]
    interface SpeechSynthesisUtterance : EventTarget {
        attribute DOMString text;
        attribute DOMString lang;
        attribute SpeechSynthesisVoice voice;
        attribute float volume;
        attribute float rate;
        attribute float pitch;

        attribute EventHandler onstart;
        attribute EventHandler onend;
        attribute EventHandler onerror;
        attribute EventHandler onpause;
        attribute EventHandler onresume;
        attribute EventHandler onmark;
        attribute EventHandler onboundary;
    };

    interface SpeechSynthesisEvent : Event {
        readonly attribute SpeechSynthesisUtterance utterance;
        readonly attribute unsigned long charIndex;
        readonly attribute float elapsedTime;
        readonly attribute DOMString name;
    };

    interface SpeechSynthesisErrorEvent extends SpeechSynthesisEvent {
        enum ErrorCode {
            "canceled",
            "interrupted",
            "audio-busy",
            "audio-hardware",
            "network",
            "synthesis-unavailable",
            "synthesis-failed",
            "language-unavailable",
            "voice-unavailable",
            "text-too-long",
            "invalid-argument",
        };

        readonly attribute ErrorCode error;
    };

    interface SpeechSynthesisVoice {
        readonly attribute DOMString voiceURI;
        readonly attribute DOMString name;
        readonly attribute DOMString lang;
        readonly attribute boolean localService;
        readonly attribute boolean default;
    };