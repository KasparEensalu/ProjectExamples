using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NoiseMechanicController : MonoBehaviour {

    private float[] stageThresholds;
    private NoiseMechanicStage[] stages;
    private static int numberOfStages = 3;
    private float currentNoise;
    [SerializeField]
    private BarScript noiseBar;
    private bool playingStage;

    void Awake () {
        playingStage = false;
        noiseBar = GameObject.Find("NoiseMechanicBar").GetComponent("BarScript") as BarScript;
        currentNoise = 0f;
        StagesSetup();
    }
	
    public void UpdateCurrentNoise(float noiseLevel) {
        if (noiseLevel >= 100f) {
            currentNoise = noiseLevel;
        } else {
            currentNoise = 0f;
        }
    }
	
	void Update () {
        noiseBar.UpdateNoise(currentNoise);
		for(int i = 0; i < numberOfStages; i++) {
            if (!HigherPriorityPlaying(i)) {
                if (currentNoise >= stageThresholds[i] && i == numberOfStages - 1){          
                    CancelEaseOut();
                    playingStage = true;
                    StartCoroutine(wait(stages[i].GetPlayTime()));
                    stages[i].PlayStage();
                } else if (currentNoise >= stageThresholds[i] && currentNoise < stageThresholds[i + 1]) {
                    CancelEaseOut();
                    playingStage = true;
                    StartCoroutine(wait(stages[i].GetPlayTime()));
                    stages[i].PlayStage();
                }
            }
        }
	}

    public bool IsPlaying() { return playingStage; }

    private IEnumerator wait(float time)
    {
        yield return new WaitForSeconds(time);
        playingStage = false;
    }

    private void StagesSetup() {
        stages = new NoiseMechanicStage[numberOfStages];
        stages[0] = GameObject.Find("FirstPersonCharacter").GetComponent("Stage1") as NoiseMechanicStage;
        stages[1] = GameObject.Find("FirstPersonCharacter").GetComponent("Stage2") as NoiseMechanicStage;
        stages[2] = GameObject.Find("FirstPersonCharacter").GetComponent("Stage3") as NoiseMechanicStage;
        stageThresholds = new float[numberOfStages];
        stageThresholds[0] = stages[0].GetLowerTreshold();
        stageThresholds[1] = stages[1].GetLowerTreshold();
        stageThresholds[2] = stages[2].GetLowerTreshold();
    }

    private bool HigherPriorityPlaying(int currentStage)
    {
        for (int i = currentStage; i < numberOfStages; i++)
        {
            if (stages[i].IsPlaying())
            {
                return true;
            }
        }
        return false;
    }

    private void CancelEaseOut() {
        for (int i = 0; i < numberOfStages; i++) {
            stages[i].CancelEaseOut();
        }
    }
}
