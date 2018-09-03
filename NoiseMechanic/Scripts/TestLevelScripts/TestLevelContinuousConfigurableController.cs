using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestLevelContinuousConfigurableController : TestLevelMultipleConfigurableController
{
    private bool playing = false;

    public void Awake()
    {
        GameObject[] allSources = GameObject.FindGameObjectsWithTag("NoiseSource");
        for (int i = 0; i < allSources.Length; i++)
        {
            if (GameObjectOnPlatform(allSources[i]))
            {
                noiseSources.Add(allSources[i]);
            }
        }
    }

    void Update()
    {
        if (Input.GetMouseButtonDown(0) && CharacterOnPlatform() && !playing)
        {
            StartNoise();
        } else if (Input.GetMouseButtonDown(0) && playing)
        {
            StopNoise();
        }
        if (CharacterOnPlatform())
        {
            CheckForDBChange();
        }

    }

    private void StartNoise()
    {
        foreach (GameObject noiseSource in noiseSources)
        {
            NoiseEmitContinuous emitter = noiseSource.GetComponent("NoiseEmitContinuous") as NoiseEmitContinuous;
            AudioSource audio = noiseSource.GetComponent("AudioSource") as AudioSource;
            emitter.EmitStart();
            audio.Play();
            playing = true;
        }
    }

    private void StopNoise()
    {
        foreach (GameObject noiseSource in noiseSources)
        {
            NoiseEmitContinuous emitter = noiseSource.GetComponent("NoiseEmitContinuous") as NoiseEmitContinuous;
            AudioSource audio = noiseSource.GetComponent("AudioSource") as AudioSource;
            emitter.EmitStop();
            audio.Stop();
            playing = false;
        }
    }
}
