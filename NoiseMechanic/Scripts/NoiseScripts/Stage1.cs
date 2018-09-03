using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stage1 : MonoBehaviour, NoiseMechanicStage {

    private PostProcess.BlinkEffect blink;
    private bool playing;
    private static float lowerTresholdInDb = 120f;
    private static float playTime = 3f;

    public void Awake()
    {
        playing = false;
        blink = GameObject.Find("FirstPersonCharacter").GetComponent("BlinkEffect") as PostProcess.BlinkEffect;
    }

    public void CancelEaseOut() { }

    public bool IsPlaying() {
        return playing;
    }

    public void PlayStage() {
        playing = true;
        blink.FadeOut();
        StartCoroutine(Wait());
    }

    IEnumerator Wait() {
        yield return new WaitForSeconds(playTime);
        playing = false;
    }

    public float GetLowerTreshold() {
        return lowerTresholdInDb;
    }

    public float GetPlayTime() {
        return playTime;
    }
}
