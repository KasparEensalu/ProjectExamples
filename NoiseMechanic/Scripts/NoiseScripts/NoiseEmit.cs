using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NoiseEmit : MonoBehaviour {
    [SerializeField]
    private float noiseInDecibels;
    private static float presetDistance = 0.1f;
    private NoiseReceive receiver;
    private GameObject character;

    private void Awake() {
        character = GameObject.Find("FirstPersonCharacter");
        receiver = GameObject.Find("FirstPersonCharacter").GetComponent(typeof(NoiseReceive)) as NoiseReceive;
    }

    public void Emit() {
        float distanceToReceiver = GetDiscanceToReceiver();
        float noiseLevel = GetDecibelsAtReceiver(distanceToReceiver);
        receiver.AddNoise(noiseLevel, distanceToReceiver);
    }

    private float GetDecibelsAtReceiver(float distance) {
        if (distance <= presetDistance) {
            return noiseInDecibels;
        } else {
            return noiseInDecibels - Mathf.Abs(20 * Mathf.Log10(presetDistance / distance));
        }
    }

    private float GetDiscanceToReceiver() {
        Vector3 receiverPos = character.transform.position;
        return Vector3.Distance(receiverPos, transform.position);
    }

    public NoiseReceive GetReceiver() {
        return receiver;
    }

    public GameObject GetCharacter() {
        return character;
    }

    public void SetNoiseLevel(float noise) {
        noiseInDecibels = noise;
    }
}
