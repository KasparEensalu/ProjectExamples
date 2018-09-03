using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stage2 : MonoBehaviour, NoiseMechanicStage
{

    private bool playing;
    private bool easingOut;

    private PostProcess.BlinkEffect blink;
    private UnityStandardAssets.CinematicEffects.LensAberrations lensAb;
    private NoiseMechanicController controller;
    private AudioSource audio;

    private static float lowerTresholdInDb = 150f;
    private static float playTime = 2f;
    private static float blur = 0.5f;
    private static float intensity = 0.5f;
    private static float intensityEaseInterval = 0.005f;
    private static float blurEaseInterval = 0.005f;
    private static float tinnitusEaseInterval = 0.005f;

    public void Awake()
    {
        blink = GameObject.Find("FirstPersonCharacter").GetComponent("BlinkEffect") as PostProcess.BlinkEffect;
        lensAb = GameObject.Find("FirstPersonCharacter").GetComponent("LensAberrations") as UnityStandardAssets.CinematicEffects.LensAberrations;
        audio = GameObject.Find("FirstPersonCharacter").GetComponent("AudioSource") as AudioSource;
        controller = GameObject.Find("FirstPersonCharacter").GetComponent("NoiseMechanicController") as NoiseMechanicController;
        easingOut = false;
        playing = false;
       
    }

    public void PlayStage() {
        playing = true;
        ActivateStageVignette();
        ActivateStageAudio();
        blink.FadeOut();
        StartCoroutine(Wait(playTime));
        easingOut = true;
    }

    IEnumerator Wait(float time) {
        yield return new WaitForSeconds(time);
        playing = false;
    }

    public bool IsPlaying() {
        return playing;
    }

    public void CancelEaseOut() {
        lensAb.vignette.intensity = 0;
        lensAb.vignette.blur = 0;
        lensAb.vignette.enabled = false;
        audio.volume = 0f;
        AudioListener.volume = 1f;
        easingOut = false;
    }

    private void Update() {
        if (easingOut && !playing) {
            PerformEaseOutStep();
        }
    }

    public float GetLowerTreshold() {
        return lowerTresholdInDb;
    }

    public float GetPlayTime() {
        return playTime;
    }

    private void ActivateStageVignette() {
        lensAb.vignette.intensity = intensity;
        lensAb.vignette.blur = blur;
        lensAb.vignette.enabled = true;
    }

    private void ActivateStageAudio() {
        audio.volume = 0.6f;
        audio.ignoreListenerVolume = true;
        AudioListener.volume = 0.6f;
        audio.Play();
    }

    private void PerformEaseOutStep() {
        if (lensAb.vignette.intensity >= intensityEaseInterval) {
            lensAb.vignette.intensity -= intensityEaseInterval;
        } else {
            lensAb.vignette.intensity = 0;
        }
        if (lensAb.vignette.blur >= blurEaseInterval) {
            lensAb.vignette.blur -= blurEaseInterval;
        } else {
            lensAb.vignette.blur = 0;
        }
        if (AudioListener.volume < 1f) {
            AudioListener.volume += 0.05f;
        } else {
            AudioListener.volume = 1f;
        }
        if (audio.volume >= tinnitusEaseInterval) {
            audio.volume -= tinnitusEaseInterval;
        } else {
            audio.volume = 0;
        }
    }
}
