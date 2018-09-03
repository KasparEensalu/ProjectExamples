using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(AudioSource))]
public class TestPillarNoiseBehaviorContinuous : MonoBehaviour
{

    private NoiseEmitContinuous emitter;
    private AudioSource audio;

    private void Awake() {
        emitter = GameObject.Find("TestNoiseSourceContinuous").GetComponent("NoiseEmitContinuous") as NoiseEmitContinuous;
        audio = GameObject.Find("TestNoiseSourceContinuous").GetComponent("AudioSource") as AudioSource;
    }

    void Update() {
        if (Input.GetMouseButtonDown(1) && !emitter.IsEmitting()) {
            start();
        } else if (Input.GetMouseButtonDown(1) && emitter.IsEmitting()) {
            stop();
        }
    }

    private void start()
    {
        audio.Play();
        emitter.EmitStart();
    }

    private void stop()
    {
        audio.Stop();
        emitter.EmitStop();
    }
}
