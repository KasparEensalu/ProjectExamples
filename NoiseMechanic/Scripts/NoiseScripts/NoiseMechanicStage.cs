using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface NoiseMechanicStage {
    void Awake();
    void PlayStage();
    bool IsPlaying();
    void CancelEaseOut();
    float GetLowerTreshold();
    float GetPlayTime();
}
