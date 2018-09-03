using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(AudioSource))]
public class TestPillarNoiseBehavior : MonoBehaviour {

    private NoiseEmit emitter;
    private AudioSource audio;

    private void Awake() {
        emitter = GameObject.Find("TestNoiseSourceImpact").GetComponent("NoiseEmit") as NoiseEmit;
        audio = GameObject.Find("TestNoiseSourceImpact").GetComponent("AudioSource") as AudioSource;
    }
	
	void Update () {
		if(Input.GetMouseButtonDown(0)) {
            audio.Play();
            emitter.Emit();
        }
	}
}
