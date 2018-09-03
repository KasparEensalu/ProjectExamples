using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HandsNoiseReduction : MonoBehaviour {

    private bool currentlyApplied = false;
    private NoiseReceive receiver;
    private static float reductionInDB = 20f;

    void Start () {
        receiver = GameObject.Find("FirstPersonCharacter").GetComponent(typeof(NoiseReceive)) as NoiseReceive;
    }
	
	void Update () {
		if (Input.GetKey(KeyCode.F) && !currentlyApplied) {
            receiver.AddReduction(reductionInDB);
            currentlyApplied = true;
        } else if (!Input.GetKey(KeyCode.F) && currentlyApplied) {
            receiver.RemoveReduction(reductionInDB);
            currentlyApplied = false;
        }
	}
}
