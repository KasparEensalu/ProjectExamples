using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stage3 : MonoBehaviour, NoiseMechanicStage
{

    private bool playing;
    private bool easingOut;
    
    private PostProcess.BlinkEffect blink;
    private UnityStandardAssets.CinematicEffects.LensAberrations lensAb;
    private NoiseMechanicController controller;
    private AudioSource audio;

    private static float lowerTresholdInDb = 180f;
    private static float playTime = 7f;
    private static float intensityEaseInterval = 0.0025f;
    private static float blurEaseInterval = 0.0025f;
    private static float tinnitusEaseInterval = 0.003f;
    private static float distortionEaseInterval = 0.05f;
    private static float distortionCenterRange = 0.2f;
    private static float intensity = 1f;
    private static float blur = 1f;

    public void Awake() {
        blink = GameObject.Find("FirstPersonCharacter").GetComponent("BlinkEffect") as PostProcess.BlinkEffect;
        lensAb = GameObject.Find("FirstPersonCharacter").GetComponent("LensAberrations") as UnityStandardAssets.CinematicEffects.LensAberrations;
        audio = GameObject.Find("FirstPersonCharacter").GetComponent("AudioSource") as AudioSource;
        controller = GameObject.Find("FirstPersonCharacter").GetComponent("NoiseMechanicController") as NoiseMechanicController;
        easingOut = false;
        playing = false;
    }

    public void PlayStage() {
        playing = true;
        ActivateStageDistortion();
        ActivateStageVignette();
        blink.FadeOut();
        ActivateStageAudio();
        StartCoroutine(Wait());
        easingOut = true;
    }

    IEnumerator Wait() {
        yield return new WaitForSeconds(playTime);
        CancelInvoke();
        lensAb.distortion.centerX = 0;
        CancelInvoke();
        playing = false;
    }

    public bool IsPlaying() {
        return playing;
    }

    public void CancelEaseOut() {
        lensAb.vignette.intensity = 0;        lensAb.vignette.blur = 0;        lensAb.vignette.enabled = false;
        lensAb.distortion.centerX = 0;
        lensAb.distortion.centerY = 0;
        lensAb.distortion.amount = 0;
        lensAb.distortion.enabled = false;
        audio.volume = 0f;
        AudioListener.volume = 1f;
        if (easingOut) {
            easingOut = false;
        }
    }

    private void Update() {
        if (easingOut && !playing) {
            PerformEaseOutStep();
        }
    }

    private void PerformEaseOutStep() {
        if (lensAb.vignette.intensity >= intensityEaseInterval) {
            lensAb.vignette.intensity -= intensityEaseInterval;
        } else {
            lensAb.vignette.intensity = 0f;
        }
        if (lensAb.vignette.blur >= blurEaseInterval) {
            lensAb.vignette.blur -= blurEaseInterval;
        } else {
            lensAb.vignette.blur = 0f;
        }
        if (audio.volume >= tinnitusEaseInterval) {
            audio.volume -= tinnitusEaseInterval;
        } else {
            audio.volume = 0f;
        }
        if (AudioListener.volume < 1f) {
            AudioListener.volume += 0.05f;
        } else {
            AudioListener.volume = 1f;
        }
        if (lensAb.distortion.amount >= distortionEaseInterval) {
            lensAb.distortion.amount -= distortionEaseInterval;
        } else {
            lensAb.distortion.amount = 0;
        }
    }

    public float GetLowerTreshold() {
        return lowerTresholdInDb;
    }

    public float GetPlayTime() {
        return playTime;
    }

    private void Distort() {
        float randomDeviationX = UnityEngine.Random.Range(-0.05f, 0.05f);
        if ((lensAb.distortion.centerX + randomDeviationX < -distortionCenterRange) || (lensAb.distortion.centerX + randomDeviationX > distortionCenterRange)) {
            lensAb.distortion.centerX = randomDeviationX;
        } else {
            lensAb.distortion.centerX += randomDeviationX;
        }
        float randomDeviationY = UnityEngine.Random.Range(-0.05f, 0.05f);
        if ((lensAb.distortion.centerY + randomDeviationY < -distortionCenterRange) || (lensAb.distortion.centerY + randomDeviationY > distortionCenterRange)) {
            lensAb.distortion.centerY = randomDeviationY;
        } else {
            lensAb.distortion.centerY += randomDeviationY;
        }
    }

    private void ActivateStageVignette() {
        lensAb.vignette.intensity = intensity;
        lensAb.vignette.blur = blur;
        lensAb.vignette.enabled = true;
    }

    private void ActivateStageDistortion() {
        lensAb.distortion.amount = 30;
        lensAb.distortion.centerX = UnityEngine.Random.Range(-distortionCenterRange, distortionCenterRange);
        lensAb.distortion.centerY = UnityEngine.Random.Range(-distortionCenterRange, distortionCenterRange);
        lensAb.distortion.enabled = true;
        InvokeRepeating("Distort", 0f, 0.1f);
    }

    private void ActivateStageAudio() {
        audio.volume = 1;
        audio.ignoreListenerVolume = true;
        AudioListener.volume = 0.1f;
        audio.Play();
    }
    }
