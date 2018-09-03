using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NoiseReceive : MonoBehaviour {
    [SerializeField]
    private float noise;
    [SerializeField]
    private float testDistance;
    private static float timeToRemove = 0.2f;
    private List<float> noiseValues;
    private NoiseMechanicController controller;
    [SerializeField]
    private float noiseReduction = 0;

    public void RemoveNoise(float noiseValue) {
        noiseValues.Remove(noiseValue);
    }

    public void AddNoise(float noiseLevel, float distance) {
        testDistance = distance;
        float randomReduction = Random.Range(15, 20);
        noiseLevel -= randomReduction;
        noiseLevel -= noiseReduction;
        if (noiseLevel > 0) {
            noiseValues.Add(noiseLevel);
            StartCoroutine(WaitAndRemove(noiseLevel));
        }
    }

	void Awake () {
        noise = 0f;
        noiseValues = new List<float>();
        controller = GameObject.Find("FirstPersonCharacter").GetComponent(typeof(NoiseMechanicController)) as NoiseMechanicController;
    }

    void Update() {
        if (noiseValues.Count == 0) {
            noise = 0f;
        } else {
            SetPressureSum();
        }
        controller.UpdateCurrentNoise(noise);
    }

    private IEnumerator WaitAndRemove(float noiseLevel) {
        yield return new WaitForSeconds(timeToRemove);
        noiseValues.Remove(noiseLevel);
    }

    private void SetPressureSum() {
        noise = 0f;
        foreach (float value in noiseValues) {
            noise += Mathf.Pow(10f, (value / 10f));
        }
        noise = 10f * Mathf.Log10(noise);
    }

    public float GetTimeToRemove() {
        return timeToRemove;
    }

    public void AddReduction(float reductionInDb) {
        noiseReduction += reductionInDb;
    }

    public void RemoveReduction(float reductionInDb) {
        noiseReduction -= reductionInDb;
    }

}
