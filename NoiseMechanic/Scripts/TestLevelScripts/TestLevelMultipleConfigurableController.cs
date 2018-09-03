using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestLevelMultipleConfigurableController : MonoBehaviour {
    
    public GameObject platform;
    public GameObject character;
    public float size;
    public List<GameObject> noiseSources;

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

    void Update() {
        if (Input.GetMouseButtonDown(0) && CharacterOnPlatform()) {
            PlayNoise();
        }
        if (CharacterOnPlatform()) {
            CheckForDBChange();
        }

    }

    public void SetNoiseLevel(float noise)
    {
        foreach (GameObject noiseSource in noiseSources)
        {
            NoiseEmit emitter = noiseSource.GetComponent("NoiseEmit") as NoiseEmit;
            emitter.SetNoiseLevel(noise);
        }
    }

    public bool CharacterOnPlatform()
    {
        return (Mathf.Abs(platform.transform.position.x - character.transform.position.x) <= size) && (Mathf.Abs(platform.transform.position.z - character.transform.position.z) <= size);
    }

    public bool GameObjectOnPlatform(GameObject gameObject)
    {
        return (Mathf.Abs(platform.transform.position.x - gameObject.transform.position.x) <= size) && (Mathf.Abs(platform.transform.position.z - gameObject.transform.position.z) <= size);
    }

    public void CheckForDBChange()
    {
        if (Input.GetKeyDown(KeyCode.F1))
        {
            SetNoiseLevel(150f);
        }
        else if (Input.GetKeyDown(KeyCode.F2))
        {
            SetNoiseLevel(160f);
        }
        else if (Input.GetKeyDown(KeyCode.F3))
        {
            SetNoiseLevel(170f);
        }
        else if (Input.GetKeyDown(KeyCode.F4))
        {
            SetNoiseLevel(180f);
        }
        else if (Input.GetKeyDown(KeyCode.F5))
        {
            SetNoiseLevel(190f);
        }
        else if (Input.GetKeyDown(KeyCode.F6))
        {
            SetNoiseLevel(200f);
        }
        else if (Input.GetKeyDown(KeyCode.F7))
        {
            SetNoiseLevel(210f);
        }
        else if (Input.GetKeyDown(KeyCode.F8))
        {
            SetNoiseLevel(220f);
        }
        else if (Input.GetKeyDown(KeyCode.F9))
        {
            SetNoiseLevel(230f);
        }
        else if (Input.GetKeyDown(KeyCode.F10))
        {
            SetNoiseLevel(240f);
        }
    }

    private void PlayNoise()
    {
        foreach (GameObject noiseSource in noiseSources)
        {
            NoiseEmit emitter = noiseSource.GetComponent("NoiseEmit") as NoiseEmit;
            AudioSource audio = noiseSource.GetComponent("AudioSource") as AudioSource;
            emitter.Emit();
            audio.Play();
        }
    }
}
